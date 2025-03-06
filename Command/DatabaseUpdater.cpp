#include <iostream>
#include <fstream>
#include <sstream>
#include <string>
#include <map>
#include <vector>
#include <stack>
#include <cstdlib>

using namespace std;

//-------------------------------------------------
// Database class
//-------------------------------------------------
class Database {
private:
    string id;
    map<string, string> data;
public:
    Database(const string &id) : id(id) {}

    string getID() const { return id; }

    bool exists(const string &key) const {
        return data.find(key) != data.end();
    }

    void add(const string &key, const string &value) {
        data[key] = value;
    }

    string get(const string &key) const {
        return data.at(key);
    }

    void update(const string &key, const string &value) {
        data[key] = value;
    }

    void remove(const string &key) {
        data.erase(key);
    }

    void display(ostream &dest) const {
        dest << "Database " << id << ":\n";
        for (auto &entry : data) {
            dest << entry.first << " | " << entry.second << "\n";
        }
    }

    bool empty() const {
        return data.empty();
    }
};

//-------------------------------------------------
// Command interface (abstract base class)
//-------------------------------------------------
class Command {
public:
    virtual bool execute() = 0;
    virtual bool undo() = 0;
    virtual ~Command() {}
};

//-------------------------------------------------
// AddCommand
//-------------------------------------------------
class AddCommand : public Command {
private:
    Database* db;
    string key;
    string value;
    bool executed;
public:
    AddCommand(Database* db, const string &key, const string &value)
        : db(db), key(key), value(value), executed(false) {}

    bool execute() override {
        if(db->exists(key)) {
            cerr << "Error: Add - key '" << key << "' already exists in database '" << db->getID() << "'.\n";
            executed = false;
        } else {
            db->add(key, value);
            executed = true;
        }
        return executed;
    }

    bool undo() override {
        if(!executed) {
            cerr << "Error: Cannot undo AddCommand for key '" << key << "' because it was not executed.\n";
            return false;
        }
        db->remove(key);
        cout << "Undid AddCommand\n";
        db->display(cout);
        return true;
    }
};

//-------------------------------------------------
// UpdateCommand
//-------------------------------------------------
class UpdateCommand : public Command {
private:
    Database* db;
    string key;
    string newValue;
    string oldValue;
    bool executed;
public:
    UpdateCommand(Database* db, const string &key, const string &newValue)
        : db(db), key(key), newValue(newValue), executed(false) {}

    bool execute() override {
        if(!db->exists(key)) {
            cerr << "Error: Update - key '" << key << "' does not exist in database '" << db->getID() << "'.\n";
            executed = false;
        } else {
            oldValue = db->get(key);
            db->update(key, newValue);
            executed = true;
        }
        return executed;
    }

    bool undo() override {
        if(!executed) {
            cerr << "Error: Cannot undo UpdateCommand for key '" << key << "' because it was not executed.\n";
            return false;
        }
        db->update(key, oldValue);
        cout << "Undid UpdateCommand\n";
        db->display(cout);
        return true;
    }
};

//-------------------------------------------------
// RemoveCommand
//-------------------------------------------------
class RemoveCommand : public Command {
private:
    Database* db;
    string key;
    string removedValue;
    bool executed;
public:
    RemoveCommand(Database* db, const string &key)
        : db(db), key(key), executed(false) {}

    bool execute() override {
        if(!db->exists(key)) {
            cerr << "Error: Remove - key '" << key << "' does not exist in database '" << db->getID() << "'.\n";
            executed = false;
        } else {
            removedValue = db->get(key);
            db->remove(key);
            executed = true;
        }
        return executed;
    }

    bool undo() override {
        if(!executed) {
            cerr << "Error: Cannot undo RemoveCommand for key '" << key << "' because it was not executed.\n";
            return false;
        }
        db->add(key, removedValue);
        cout << "Undid RemoveCommand\n";
        db->display(cout);
        return true;
    }
};

//-------------------------------------------------
// MacroCommand
//-------------------------------------------------
class MacroCommand : public Command {
private:
    vector<Command*> commands;
    bool executed;
public:
    MacroCommand() : executed(false) {}

    void addCommand(Command* cmd) {
        commands.push_back(cmd);
    }

    bool execute() override {
        cout << "Beginning a Macro\n";
        executed = true;
        for(auto cmd : commands) {
            cmd->execute();
        }
        cout << "Ending a Macro\n";
        return executed;
    }

    bool undo() override {
        if(!executed) {
            cerr << "Error: Cannot undo MacroCommand because it was not executed.\n";
            return false;
        }
        cout << "Begin Undoing Macro\n";
        // Undo in reverse order
        for (auto it = commands.rbegin(); it != commands.rend(); ++it) {
            (*it)->undo();
        }
        cout << "End Undoing Macro\n";
        return true;
    }

    ~MacroCommand() {
        for (auto cmd : commands) {
            delete cmd;
        }
    }
};

//-------------------------------------------------
// Main processing
//-------------------------------------------------
int main(int argc, char* argv[]) {
    if(argc != 2) {
        cerr << "Usage: " << argv[0] << " <command_file>\n";
        return 1;
    }

    ifstream infile(argv[1]);
    if(!infile) {
        cerr << "Error: Could not open file " << argv[1] << "\n";
        return 1;
    }

    map<string, Database*> databases;
    vector<Command*> mainCommands;
    stack<MacroCommand*> macroStack;

    string line;
    while (getline(infile, line)) {
        if(line.empty()) continue;
        istringstream iss(line);
        string token;
        iss >> token;

        // Check for macro tokens
        if(token == "B") {
            // Begin macro: create new MacroCommand and push on stack
            MacroCommand* macro = new MacroCommand();
            macroStack.push(macro);
            continue;
        } else if(token == "E") {
            // End macro: pop from stack and add to parent or mainCommands
            if(macroStack.empty()) {
                cerr << "Error: 'E' encountered without matching 'B'.\n";
                continue;
            }
            MacroCommand* finishedMacro = macroStack.top();
            macroStack.pop();
            if(!macroStack.empty()) {
                macroStack.top()->addCommand(finishedMacro);
            } else {
                mainCommands.push_back(finishedMacro);
            }
            continue;
        }

        // Otherwise token must be a command letter: A, U, or R.
        char cmdType = token[0];
        string dbID, key;
        iss >> dbID >> key;
        string value;
        if(cmdType == 'A' || cmdType == 'U') {
            // For these commands, the rest of the line is the value.
            getline(iss, value);
            // Remove leading whitespace
            if(!value.empty() && value[0] == ' ')
                value.erase(0, 1);
        }

        // Create (or get) the target database
        if(databases.find(dbID) == databases.end()) {
            databases[dbID] = new Database(dbID);
        }
        Database* db = databases[dbID];

        Command* cmd = nullptr;
        if(cmdType == 'A') {
            cmd = new AddCommand(db, key, value);
        } else if(cmdType == 'U') {
            cmd = new UpdateCommand(db, key, value);
        } else if(cmdType == 'R') {
            cmd = new RemoveCommand(db, key);
        } else {
            cerr << "Error: Unknown command type '" << cmdType << "'.\n";
            continue;
        }

        // If inside a macro, add command there; else add to mainCommands.
        if(!macroStack.empty()) {
            macroStack.top()->addCommand(cmd);
        } else {
            mainCommands.push_back(cmd);
        }
    }

    if(!macroStack.empty()) {
        cerr << "Error: One or more 'B' tokens were not closed by 'E'.\n";
    }

    // Execute all commands in order
    for(auto cmd : mainCommands) {
        cmd->execute();
    }

    // Display databases after execution
    cout << "\nContents of Databases:\n";
    for(auto &pair : databases) {
        pair.second->display(cout);
    }

    // Undo all commands in reverse order
    for (auto it = mainCommands.rbegin(); it != mainCommands.rend(); ++it) {
        (*it)->undo();
    }

    // Final verification: all databases should be empty.
    cout << "\nContents of Databases:\n";
    for(auto &pair : databases) {
        pair.second->display(cout);
    }

    // Cleanup: free commands and databases.
    for(auto cmd : mainCommands) {
        delete cmd;
    }
    for(auto &pair : databases) {
        delete pair.second;
    }

    return 0;
}
