import pymel.core as pc
import maya.cmds as mc
import shutil
import unicodedata 
import os


"""
Script: ProjectFixer.py

Author: Sean Meginness

Purpose:
    Prompts user to set project.
    
    Re-maps all texture files that are outside
    that project to the sourceimages folder.
    
    Re-maps all references to scenes/seferences folder.
   
    
"""
def projFixer(newDir, doTextures, doReferences):
    pc.workspace (newDir, openWorkspace=True)
    
    #get project directory
    pwd = pc.workspace(q=1,rd=1)
    
    
   
    if doTextures:
        TexArray = pc.ls( textures=True )
        
        #names of file nodes
        for eachFile in TexArray: 
            
            #path of texture
            currentFile = pc.getAttr("%s.fileTextureName" % eachFile)
            
            basename = currentFile.split('/')[-1] # gets just the filename
            exists = os.path.exists(pwd+"/sourceImages/"+basename)
            if not exists:
                shutil.copy(currentFile, "%s/sourceimages/" % pwd) # copy file to current project
            pc.setAttr(eachFile+".fileTextureName", "sourceimages/%s" % basename, typ= "string") # re-path texture
    if doReferences:
        RefArray = mc.file( q =1, r = 1)
		#if there are references:
        if RefArray:
			#create references directory if it does not already exist
            if not os.path.exists("%s/scenes/references/" % pwd):
                os.mkdir("%s/scenes/references/" % pwd)
            for eachRef in RefArray:
                newRef = ""
				#this for loop fixes wierd duplicate reference issues
                for x in range(len(eachRef.split('.'))-1):
                     newRef += eachRef.split('.')[x]+"."
                newRef += eachRef.split('.')[-1][:2]
                eachRef = newRef
                
                file2copy = eachRef.split('/')[-1]
                if not pwd+"scenes/references" in eachRef: #checks if reference is outside project
                    print "copying " + file2copy + "\t" + pwd
                    shutil.copy(eachRef, "%s/scenes/references" % pwd) # copy file to current project
                mc.file( "scenes/references/"+file2copy, r=True,ns= file2copy.split('.')[0])
                mc.file( eachRef, rr=True )
 
projFixer("D:/maya_projects/projects/default/", False, True)