import numpy as np
import matplotlib.pyplot as plt

# test print
my_text = 'hello world'
print(my_text)

# variables
x = np.arange(0,2*np.pi,np.pi/100)
y = np.sin(x)

# Display
plt.figure()
plt.plot(x,y)
plt.title('mon premier graphique')
plt.show()
