#include <iostream>
#include <Windows.h>
#include <fstream>
#include <string>
#include <vector>
#include <sstream>
#pragma comment(lib, "user32.lib")
using namespace std;

// Config variables
const bool debug = FALSE;
string savePath = "data.txt";

HHOOK hook;
KBDLLHOOKSTRUCT kbdstruct;
bool close;
long presses[30][31];
//0-25 == a-z
//26   == ;: = 59
//27   == '" = 39
//28   == ,< = 44
//39   == .> = 46
//-1   == other/none
int currChar = -1;
int prevChar = -1;


static void SaveData() {
    string save = "";
    for (int i = 0; i < 30; i++) {
        for (int j = 0; j < 31; j++) {
            save += (to_string(presses[i][j]) + ",");
        }
        save += "\n";
    }

    ofstream fileOut(savePath);
    fileOut << save;
    fileOut.close();
}

static void LoadData() {
    string save;
    ifstream fileIn(savePath);

    if (!fileIn) {
        cout << "No save detected, creating new save" << endl;
        return;
    }
    cout << "Save found, loading data" << endl;

    int i = 0;
    while (getline(fileIn, save)) {

        stringstream sstream(save);
        string temp;
        int j = 0;
        while (getline(sstream, temp, ',')) {
            if (i >= 30 || j >= 31) {
                cout << "save format does not match, exiting program" << endl;
                exit(1);
            }
            presses[i][j] = stoi(temp);
            j++;
        }
        i++;
    }
    if (debug) {
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 31; j++) {
                cout << presses[i][j] << ",";
            }
            cout << endl;
        }
    }
}

static void ProcessChar(int c) {
    if (c == 0) return; // Program does not account for modifier keys
    prevChar = currChar;
    currChar = -1; // Stays as 31 unless relevant key is pressed
    // Switch case to account for punctuation
    switch (c) {
    case 59:
        currChar = 26;
        break;
    case 39:
        currChar = 27;
        break;
    case 44:
        currChar = 28;
        break;
    case 46:
        currChar = 29;
        break;
    }
    //-97 converts c into correct integer for the array
    c -= 97;
    if (0 <= c && 25 >= c) {
        currChar = c;
    }
    if (currChar != -1) {
        if (prevChar != -1) {
            presses[prevChar][currChar]++;
            if (debug) { cout << prevChar << " " << currChar << endl; }
        }
        presses[currChar][30]++;
        if (debug) { cout << currChar << " " << 30 << endl; }
    }
    // saves data when space is pressed
    if (c == -65) {
        SaveData();
    }

    if (debug) { cout << "currChar == " << currChar << endl; }
    return;
}

LRESULT CALLBACK KeyboardProc(int nCode, WPARAM wParam, LPARAM lParam) {
    // If < 0, do not handle the hook
    if (nCode < 0) {
        return CallNextHookEx(hook, nCode, wParam, lParam);
    }

    //test, if close is true, end hook
    if (close) {
        PostQuitMessage(0);
    }

    kbdstruct = *((KBDLLHOOKSTRUCT*)lParam);

    BYTE KeyboardState[256] = {};
    wchar_t unicodeCharacter[3] = {};
    /* Modifier keys, not needed for currCharent plans
      GetKeyState(VK_SHIFT); // shift modifier
      GetKeyState(VK_MENU);  // ctrl modifier
    */
    bool works = GetKeyboardState(KeyboardState);
    if (!works) {
        cout << "Keyboard state error\n";
        return CallNextHookEx(hook, nCode, wParam, lParam);
    }

    ToUnicodeEx((UINT)kbdstruct.vkCode, (UINT)kbdstruct.scanCode, KeyboardState, unicodeCharacter,
        sizeof(unicodeCharacter) / sizeof(*unicodeCharacter) - 1, (UINT)kbdstruct.flags, GetKeyboardLayout(0));

    switch (wParam) {
    case WM_KEYDOWN:
        // Handles Keycodes
        if (debug) { cout << unicodeCharacter[0] << " key was pressed!\n"; }
        ProcessChar((int)unicodeCharacter[0]);
        break;
    case WM_KEYUP:
        break;
    }

    return CallNextHookEx(hook, nCode, wParam, lParam);
}

int main() {
    LoadData();
    hook = SetWindowsHookEx(WH_KEYBOARD_LL, KeyboardProc, NULL, NULL);

    cout << "Keylogger is now running. Keypresses are saved automatically every time space is pressed.\n"<<
        "You can find the save file in "<<savePath<<endl;
    cout << "This program only saves your total number of presses for each key, along with all 2-key sequences.\nIt does not save full words" << endl;
    cout << "to stop this application, simply close this window." << endl;

    MSG msg;
    while (GetMessage(&msg, NULL, NULL, NULL)) { // Message Loop
        TranslateMessage(&msg);
        cout << "message loop\n";
        DispatchMessage(&msg);
    }

    return 0;
}
