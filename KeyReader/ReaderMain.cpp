#include <iostream>
#include <Windows.h>
#include <fstream>
#include <string>
#pragma comment(lib, "user32.lib")
using namespace std;

HHOOK hook;
KBDLLHOOKSTRUCT kbdstruct;
bool close;
long presses[30][31];
//0-25 == a-z
//26   == ;:
//27   == '"
//28   == ,<
//39   == .>
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

    ofstream fileOut("data.txt");
    fileOut << save;
    fileOut.close();
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
            cout << prevChar << " " << currChar << endl;
        }
        presses[currChar][30]++;
        cout << currChar << " " << 30 << endl;
    }
    if (c == -65) {
        SaveData();
    }

    cout << "currChar == " << currChar << endl;
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
        cout << unicodeCharacter[0] << " key was pressed!\n";
        ProcessChar((int)unicodeCharacter[0]);
        break;
    case WM_KEYUP:
        break;
    }

    return CallNextHookEx(hook, nCode, wParam, lParam);
}

int main() {
    hook = SetWindowsHookEx(WH_KEYBOARD_LL, KeyboardProc, NULL, NULL);

    MSG msg;
    while (GetMessage(&msg, NULL, NULL, NULL)) { // Message Loop
        TranslateMessage(&msg);
        cout << "message loop\n";
        DispatchMessage(&msg);
    }

    return 0;
}
