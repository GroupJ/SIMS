====================================================================
      ___           ___                       ___           ___     
     /\__\         /\__\                     /\  \         /\__\    
    /:/ _/_       /:/ _/_       ___         |::\  \       /:/ _/_   
   /:/ /\  \     /:/ /\  \     /\__\        |:|:\  \     /:/ /\  \  
  /:/ /::\  \   /:/ /::\  \   /:/__/      __|:|\:\  \   /:/ /::\  \ 
 /:/_/:/\:\__\ /:/_/:/\:\__\ /::\  \     /::::|_\:\__\ /:/_/:/\:\__\
 \:\/:/ /:/  / \:\/:/ /:/  / \/\:\  \__  \:\~~\  \/__/ \:\/:/ /:/  /
  \::/ /:/  /   \::/ /:/  /   ~~\:\/\__\  \:\  \        \::/ /:/  / 
   \/_/:/  /     \/_/:/  /       \::/  /   \:\  \        \/_/:/  /  
     /:/  /        /:/  /        /:/  /     \:\__\         /:/  /   
     \/__/         \/__/         \/__/       \/__/         \/__/  

Project Manager:
========================================
Ragil Budi, Prasetya	20378332

Members:
========================================
Guo’An James, Yan	10770283
Joel Mark, Sturmfels	20164638
Arjun Kishore, Kannan	20553004
Sze Hong, Leow	        20651203
Nelson Kon, Yiap	20256437

====================================================================

Description
===========
S:SIMS is a program that parses data created by the Cameca IMS 1280 instrument. The program supports importing the data from the IMS, displaying the data on multiple windows, plotting a x-y chart and exporting  the raw and processed data. 


========================================

README
========================

When you build an Java application project that has a main class, the IDE
automatically copies all of the JAR
files on the projects classpath to your projects dist/lib folder. The IDE
also adds each of the JAR files to the Class-Path element in the application
JAR files manifest file (MANIFEST.MF).

The user can run the program via the shortcut that was created during a full installation of the program.

Alternatively, to run the project from the command line, go to the dist folder and
type the following:

java -jar "SIMS2.jar" 

Notes:

* If two JAR files on the project classpath have the same name, only the first
JAR file is copied to the lib folder.
* Only JAR files are copied to the lib folder.
If the classpath contains other types of files or folders, none of the
classpath elements are copied to the lib folder. In such a case,
you need to copy the classpath elements to the lib folder manually after the build.
* If a library on the projects classpath also has a Class-Path element
specified in the manifest,the content of the Class-Path element has to be on
the projects runtime path.
* To set a main class in a standard Java project, right-click the project node
in the Projects window and choose Properties. Then click Run and enter the
class name in the Main Class field. Alternatively, you can manually type the
class name in the manifest Main-Class element.