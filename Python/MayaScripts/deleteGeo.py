import pymel.core as mc

geo = mc.ls(type = "mesh")
geoTrans = []
for i in geo:
    geoTrans.append(mc.listRelatives(i, parent=True))
mc.select(geoTrans)
mc.delete()