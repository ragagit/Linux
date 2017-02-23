// UACLauncher.cpp : Defines the entry point for the application.
//

#include "stdafx.h"
#include "UACLauncher.h"
#include "shellapi.h"
#include "string.h"
#include <iostream>
#include "shlwapi.h"

#define MAXPATHLEN				(MAX_PATH * 2) + 2


bool GetOSVersion()
{
    OSVERSIONINFO osvi;
    BOOL bIsVistaOrHigher;

    ZeroMemory(&osvi, sizeof(OSVERSIONINFO));
    osvi.dwOSVersionInfoSize = sizeof(OSVERSIONINFO);

    GetVersionEx(&osvi);

    bIsVistaOrHigher = (osvi.dwMajorVersion >= 6);

	/*  Windows 7	6.1
		Windows Server 2008 R2	6.1
		Windows Server 2008	6.0
		Windows Vista	6.0
		Windows Server 2003 R2	5.2
		Windows Server 2003	5.2
		Windows XP 64-Bit Edition	5.2
		Windows XP	5.1
		Windows 2000 5.0 */
    
	return bIsVistaOrHigher;
}



int APIENTRY _tWinMain(HINSTANCE hInstance,
                       HINSTANCE hPrevInstance,
                       LPTSTR    lpCmdLine,
                       int       nCmdShow)
{
	LPWSTR *argv;
	int argc = 0;

	std::wstring launchApp = L"";
	std::wstring commandStr = L"";
	
	argv = CommandLineToArgvW(GetCommandLineW(), &argc);
	if(argv == NULL || argc < 2) {		
		return 1;
	}

	wchar_t szServicePath[MAXPATHLEN];
	GetModuleFileName(NULL, szServicePath, MAXPATHLEN);
	PathRemoveFileSpecW(szServicePath);
	PathAddBackslashW(szServicePath);		
	PathAppendW(szServicePath, L"PrinterOn Controller.exe");
	launchApp = szServicePath;

	/*for (int i = 0; i < argc; i++)
	{
		MessageBox(NULL, argv[i], L"", 0);
		GetTotalLength += wcslen(argv[i]);
	}*/

	bool bIsUserAcccessDialogRequired = false;
	bIsUserAcccessDialogRequired = GetOSVersion();
	
	if (argc == 2)
	{
		commandStr += L"\"";		
		commandStr += argv[1];		
		commandStr += L"\"";
		//MessageBox(NULL, commandStr.c_str(), L"", 0);		
	}
	else if (argc == 3)	
	{	
		commandStr += argv[2];
		commandStr += L" \"";
		commandStr += argv[1];
		commandStr += L"\"";	
		//MessageBox(NULL, commandStr.c_str(), L"", 0);		
	}
	else if (argc == 7)
	{
		commandStr += argv[6];
		commandStr += L" \"";
		commandStr += argv[1];
		commandStr += L"\"";	
		commandStr += L" \"";
		commandStr += argv[2];
		commandStr += L"\"";
		commandStr += L" \"";
		commandStr += argv[3];
		commandStr += L"\"";
		commandStr += L" \"";
		commandStr += argv[4];
		commandStr += L"\"";
		commandStr += L" \"";
		commandStr += argv[5];
		commandStr += L"\"";
		//MessageBox(NULL, commandStr.c_str(), L"", 0);
		
	}
	else 
		return 2;

	if (bIsUserAcccessDialogRequired)
		ShellExecute(NULL, L"runas", launchApp.c_str(), commandStr.c_str(), 0, SW_SHOWNORMAL);
	else
		ShellExecute(NULL, L"open", launchApp.c_str(), commandStr.c_str(), 0, SW_SHOWNORMAL);
	
	return 0;
}