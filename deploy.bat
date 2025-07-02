@echo off
setlocal

set TOMCAT_HOME="C:\apache-tomcat-10.1.28"
set WAR_PATH=target\bibliotheque.war

echo Nettoyage et compilation...
call mvn clean package

if not exist %WAR_PATH% (
    echo ERREUR: Fichier WAR non généré!
    pause
    exit /b 1
)

echo Déploiement vers Tomcat...
copy /Y %WAR_PATH% %TOMCAT_HOME%\webapps\


endlocal
pause