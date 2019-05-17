import pymel.core as pmc
import math as m #not currently used, but will be needed to add angle function
"""
Script: bouncingBall

Author: Sean Meginness

Purpose: Create 2D projectile motion with a python script in Maya.

Features: 
    Can be used on any objectType
    Initial velocity can be specified
    
Limitations/things to improve:
    Only angle is currently 45 degrees
    translateZ is locked to zero
"""
def physicsBounce(Obj,Vo,timeOffset = 0, xOffset = 0, theta = 45):
    Vox = Vo * m.cos(m.radians(theta))
    Voy = Vo * m.sin(m.radians(theta))
    a = -9.8
    t = (int)(-Voy/a)*10
    Dy = Voy*t + 1/2*(a*t*t)
    Dx = Vox*t
    #print t, Dy, Dx                 #DEBUG PRINT
    
    pmc.currentTime(t+timeOffset)
    pmc.xform(Obj,t=(Dx + xOffset,Dy,0), a = 1)
    pmc.setKeyframe(Obj, an = 1, inTangentType = "auto", outTangentType = "auto") #set key at peak
    
    pmc.currentTime(2*t+timeOffset)
    pmc.xform(Obj,t=(2*Dx + xOffset,0,0), a = 1)
    pmc.setKeyframe(Obj, an = 1, inTangentType = "linear", outTangentType = "linear")#set key at base of next jump

    if (Vo > 5):
        print "recursion"                    #DEBUG PRINT
        physicsBounce(Obj = Obj,Vo = Vo/1.2,timeOffset = 2*t+timeOffset, xOffset = 2*Dx + xOffset, theta= theta)
    
        
    
    
    
startFrame = pmc.playbackOptions(q=1, minTime=1)
endFrame = pmc.playbackOptions(q=1, maxTime=1)
currentFrame = startFrame

myShpere = pmc.polySphere()
pmc.currentTime(startFrame)
pmc.setKeyframe(myShpere, at="translateX", v = 0, outTangentType = "linear")#setting initial keys
pmc.setKeyframe(myShpere, at="translateY", v = 0, outTangentType = "linear")#         is required

physicsBounce(Obj = myShpere, Vo = 24, theta = 45)

#pmc.system.saveAs("ProcAnimation_sMeginness.ma")