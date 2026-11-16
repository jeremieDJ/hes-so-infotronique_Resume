import matplotlib.pyplot as plt
import numpy as np
from mpl_toolkits.mplot3d import Axes3D

# Generate data
x = np.linspace(0, 10, 100)
y1 = np.sin(x)
y2 = 0.5 * x
y3 = np.cos(x)

# Create a figure and axis
fig, ax = plt.subplots()

# Plot the first curve with a blue solid line
ax.plot(x, y1, label='sin(x)', color='blue', linewidth=2)

# Plot the second curve with a red dashed line
ax.plot(x, y2, label='0.5*x', color='red', linestyle='--', linewidth=2)

# Plot the third curve with a green dotted line
ax.plot(x, y3, label='cos(x)', color='green', linestyle=':', linewidth=2)

# Add a title and labels
ax.set_title('Test installation')
ax.set_xlabel('X-axis')
ax.set_ylabel('Y-axis')

# Add a legend
ax.legend()

# Add text annotations
ax.text(4, 1.5, 'Congratulation!', fontsize=12, color='purple')

# Add a shaded region between curves
ax.fill_between(x, y1, y3, where=(y1 > y3), interpolate=True, color='gray', alpha=0.3)

# Add gridlines
ax.grid(True, linestyle='--', alpha=0.7)

# Show the plot
plt.show()