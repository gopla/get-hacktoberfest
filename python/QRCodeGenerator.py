# Dependency : pyqrcode


# importing the required module 
import pyqrcode

# data stores the encoded data. It can be anything (url, plain-text, numbers, etc)
data="www.github.com"

# Use create() available in pyqrcode
qr=pyqrcode.create(data)

# Now save it in png format
qr.png('qr_code.png',scale=8)