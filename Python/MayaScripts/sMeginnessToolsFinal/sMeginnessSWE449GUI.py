import pymel.core as pm
import sys
import os
dirpath = os.getcwd()
sys.path.append(dirpath)
import sMeginness_MidtermFunction 
#from sMeginness_MidtermFunction import projFixer
import OptionsWindowBaseClass
from functools import partial

"""
Script: sSeginnessSWE449GUI.py

Author: Sean Meginness

Purpose: Creates a GUI for existing 
	Project Fixer tool

"""





"""
callFunc is a utility function to
	use the projFixer from sMeginness_MidtermFunction
"""
def callFunc(folder, texYN, refYN, *args):
    f = folder.getText() + "/"
    print f
    t =texYN.getValue()
    print t
    r = refYN.getValue()
    print r
    sMeginness_MidtermFunction.projFixer(f,t,r)


"""
GUI that inherits from OptionsWindowBaseClass
"""
class ProjFixerGUI(OptionsWindowBaseClass.OptionsWindow):
    def ___init__(self):
        self.window = "sMeginness_Window"
        self.title = "Project Fixer"
        self.size = (400,350)
        self.actionName = "Apply and Close"
        self.applyName = "Apply"
    def displayOptions(self):
                                   
        #select a folder
        self.mainRCL = pm.rowColumnLayout(adj = 1)
        pm.frameLayout(l = "Project", h = 50, parent = self.mainRCL)
        self.folder = pm.windows.folderButtonGrp(label = "Select Folder", cl3 = ("center","left","left"))
        pm.setParent(self.mainRCL)
        
        
        #textures / references checkBoxes
        pm.frameLayout(l = "Types", h = 80,parent = self.mainRCL)
        pm.rowColumnLayout( adj = True)
        self.texYN = pm.checkBox(l = "textures")
        self.refYN = pm.checkBox(l = "references")
        pm.setParent(self.mainRCL)
        
    def actionCmd(self, *args):
        callFunc( self.folder, self.texYN, self.refYN)
        pm.deleteUI(self.window,window=True)

    def applyBtnCmd(self, *args):
        callFunc( self.folder, self.texYN, self.refYN)

    """
    GUI for midterm END
    """
def sMeginnessSWE449GUI():
	sMeginness = ProjFixerGUI()
	sMeginness.create()
	
sMeginnessSWE449GUI()