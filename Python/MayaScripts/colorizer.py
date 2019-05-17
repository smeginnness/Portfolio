import pymel.core as mc
import random as r

def makeColors(obj,freq=3, baseColor = [r.random(),r.random(),r.random()]):
    numVert = len(obj[0].vtx)
    print numVert
    
    mc.select(clear =1)
    for i in range(0, numVert):
        vtxName = obj[0]+".vtx[%d]"%i
        mc.select(vtxName, add = 1)
        
    mc.polyColorPerVertex(colorRGB = baseColor, colorDisplayOption = 1)
        
    mc.select(clear =1)
    
    for i in range(0, numVert,freq):
        vtxName = obj[0]+".vtx[%d]"%i
        mc.select(vtxName)
        
        mc.polyColorPerVertex(colorRGB = [r.random(),r.random(),r.random()], colorDisplayOption = 1)
        
    mc.select(clear =1)

mySphere1 = mc.polyTorus()
makeColors(mySphere1)
mc.xform(mySphere1, t = (2,0,0))

mySphere2 = mc.polyHelix(r =0.2,h=3)
makeColors(mySphere2, freq = 2,baseColor = [0.5,0,0.2])