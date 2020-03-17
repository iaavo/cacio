
call "C:\Program Files\Microsoft SDKs\Windows\v7.1\Bin\SetEnv.cmd" /Release /win7 /x64
:: Uncomment if you want to specify other JDK
:: SET JAVA_HOME="C:\Program Files\Java\jdk1.7.0_05"

:: ------------- ::
:: Setup above ^ ::
:: ------------- ::

SET linkops=/D_STATIC_CPPLIB /D_DISABLE_DEPRECATE_STATIC_CPPLIB /W3 /D__MEDIALIB_OLD_NAMES /D__USE_J2D_NAMES /DMLIB_NO_LIBSUNMATH /DUNICODE /D_UNICODE /DMLIB_OS64BIT /DNDEBUG /DWIN32 /DIAL /D_LITTLE_ENDIAN /D_AMD64_ /Damd64 /DWIN32_LEAN_AND_MEAN

SET incl="/I%JAVA_HOME%/include"
SET incl=%incl% "/I%JAVA_HOME%/include/win32"
SET incl=%incl% "/I../../c"

:: SET javaSrcDir=C:\openjdk\jdk8
:: SET incl=/I%javaSrcDir%\jdk\src\share\javavm\export
:: SET incl=%incl% /I%javaSrcDir%\jdk\src\windows\javavm\export
:: SET incl=%incl% /I%javaSrcDir%\jdk\src\share\native\sun\java2d
:: SET incl=%incl% /I%javaSrcDir%\jdk\src\share\native\common
:: SET incl=%incl% /I%javaSrcDir%\jdk\src\windows\native\common

cd build/obj
cl /nologo %linkops% /EHsc /Zc:wchar_t- /MD ../../c/*.c %incl% /link /subsystem:console /DLL /OUT:../native/cacio-web.dll ../../lib/awt.lib
@pause