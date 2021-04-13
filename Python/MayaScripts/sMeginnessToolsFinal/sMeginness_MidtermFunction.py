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
        if RefArray:
            if not os.path.exists("%s/scenes/references/" % pwd):
                os.mkdir("%s/scenes/references/" % pwd)
            for eachRef in RefArray:
                #jank = eachRef #should be removeable
                newRef = ""
                #for x in range(len(jank.split('.'))-1): #new version below should do same thing without extraneous variable
                for x in range(len(eachRef.split('.'))-1):
                     newRef += eachRef.split('.')[x]+"."#fixes wierd duplicate issues
                newRef += eachRef.split('.')[-1][:2]
                eachRef = newRef
                
                file2copy = eachRef.split('/')[-1]
                if not pwd+"scenes/references" in eachRef: #checks if reference is outside project
                    print "copying " + file2copy + "\t" + pwd
                    shutil.copy(eachRef, "%s/scenes/references" % pwd) # copy file to current project
                mc.file( "scenes/references/"+file2copy, r=True,ns= file2copy.split('.')[0])
                mc.file( eachRef, rr=True )
 
projFixer("D:/maya_projects/projects/default/", False, True)