call ./runcrud
if "%ERRORLEVEL%" == "0" goto startchrome
echo.
echo RUN CRUD has errors - breaking work
goto fail

:startchrome
start chrome
start "webpage name" "http://localhost:8080/crud/v1/tasks/"
goto end

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished