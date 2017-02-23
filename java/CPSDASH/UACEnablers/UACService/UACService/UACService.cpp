// UACLauncher.cpp : Defines the entry point for the application.
//

#include "stdafx.h"
#include "UACService.h"
#include "string.h"
#include <iostream>
#include "shellapi.h"
#include "shlwapi.h"

#define MAXPATHLEN				(MAX_PATH * 2) + 2

std::string ws2s(const std::wstring& s)
{
 int len;
 int slength = (int)s.length() + 1;
 len = WideCharToMultiByte(CP_ACP, 0, s.c_str(), slength, 0, 0, 0, 0);
 char* buf = new char[len];
 WideCharToMultiByte(CP_ACP, 0, s.c_str(), slength, buf, len, 0, 0);
 std::string r(buf);
 delete[] buf;
 return r;
}


int APIENTRY _tWinMain(HINSTANCE hInstance,
                       HINSTANCE hPrevInstance,
                       LPTSTR    lpCmdLine,
                       int       nCmdShow)
{
	LPWSTR *argv;
	int argc = 0;	

	std::wstring command = L""; 
	
	argv = CommandLineToArgvW(GetCommandLineW(), &argc);
	if(argv == NULL || argc < 2) {				
		return 1;
	}

	if (argc == 4)
	{
		command += argv[1];
		command += L" ";
		command += argv[2];
		command += L" \"";
		command += argv[3];
		command += L"\"";		
		system(ws2s(command).c_str());
	}
	else if (argc == 2)
	{	
		ShellExecute(NULL, L"open", argv[1], 0, 0, SW_SHOWNORMAL);	
		std::wstring strPath = PathFindFileName(argv[1]);
		if (wcscmp(strPath.c_str(), L"PrintDeliveryStation.exe") == 0
			|| wcscmp(strPath.c_str(), L"Pds.exe") == 0)
		{
			Sleep(2000);
			ShellExecute(NULL, L"open", argv[1], 0, 0, SW_SHOWNORMAL);	
		}
	}
	else if (argc == 8) // ReImport Config Information
	{
		//command += L"cmd /k ";

		command += argv[1];
		command += L" ";
		command += argv[2];
		command += L" \"";
		command += argv[3];
		command += L"\"";
		command += L" \"";
		command += argv[4];
		command += L"\"";
		command += L" \"";
		command += argv[5];
		command += L"\"";
		command += L" \"";
		command += argv[6];
		command += L"\"";
		command += L" \"";
		command += argv[7];
		command += L"\"";
		
		system("net stop \"Apache Tomcat\""); //Stop the Tomcat service

		/*CPS DashBoard Code - for Reference
		String cpsPath = RegistryAccess.searchComp(CPSDashConstants.CPS_COMP).getCompPath(); [CPS - Path]
			"C:\Program Files (x86)\PrinterOn Corporation\Apache Tomcat\webapps"
		String configPath = "\"" + cpsPath + "\\cps" + "\"";
			"C:\Program Files (x86)\PrinterOn Corporation\Apache Tomcat\webapps\cps\"
		*/

		//MessageBox(NULL, argv[5], L"", 0); // configPath information from DashBoard

		wchar_t szBaseConfFilePath[MAXPATHLEN];
		wcscpy_s(szBaseConfFilePath, MAXPATHLEN, argv[5]);
		PathRemoveFileSpecW(szBaseConfFilePath); // removing cps from path
		PathRemoveFileSpecW(szBaseConfFilePath); // removing webapps
		PathAddBackslashW(szBaseConfFilePath);// Adding slash	
		PathAppendW(szBaseConfFilePath, L"Conf"); // Adding 'Conf' to the Path
		PathAddBackslashW(szBaseConfFilePath);// Adding slash	- Base Conf Path	

		//MessageBox(NULL, szBaseConfFilePath, L"", 0); // sourceWar information from DashBoard		
		
		std::wstring strBaseConfigPath = L"";
		strBaseConfigPath = szBaseConfFilePath;		
		strBaseConfigPath += L"cps_config.xml";
		DeleteFile(strBaseConfigPath.c_str()); // Deleting Config File

		strBaseConfigPath = L"";
		strBaseConfigPath = szBaseConfFilePath;		
		strBaseConfigPath += L"cps_printer_db.xml"; // Deleting DB File
		DeleteFile(strBaseConfigPath.c_str());

		strBaseConfigPath = L"";
		strBaseConfigPath = szBaseConfFilePath;		
		strBaseConfigPath += L"z.zd_"; //Deleting Password File
		DeleteFile(strBaseConfigPath.c_str());

		//MessageBox(NULL, command.c_str(), L"", 0);
		
		Sleep(500); // Wait for half a sec
		system(ws2s(command).c_str());//Do whatever that is needed		
		Sleep(500); // Wait for half a sec

		system("net start \"Apache Tomcat\""); // Start the service		
	}
	else
		return 1;

//	for (int i = 0; i < argc; i++)
//		MessageBox(NULL, argv[i], L"", 0);

	return 0;
}