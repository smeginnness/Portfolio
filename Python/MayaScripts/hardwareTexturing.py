import pymel.core as pm

myPanels = pm.getPanel(type = "modelPanel")
for eachPanel in myPanels:
    if(pm.modelEditor(eachPanel,q=1,displayTextures=1)):
        pm.modelEditor(eachPanel,e=1,displayTextures=0)
    else:
        pm.modelEditor(eachPanel,e=1,displayTextures=1)
