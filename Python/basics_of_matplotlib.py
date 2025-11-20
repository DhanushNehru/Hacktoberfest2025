"""
Basics of Matplotlib

This script demonstrates the fundamental concepts of Matplotlib, a popular Python library for creating static, animated, and interactive visualizations.

Matplotlib provides a MATLAB-like interface for plotting data. Key components include:
- Figure: The overall window or page that holds the plot.
- Axes: The area where data is plotted, including x and y axes.
- Plot types: Line plots, scatter plots, bar plots, histograms, etc.

To use Matplotlib, you typically import it as 'plt' from matplotlib.pyplot.

This script includes examples of basic plots with docstrings explaining each function.
"""

import matplotlib.pyplot as plt
import numpy as np  # For generating sample data

def line_plot_example():
    """
    Demonstrates a basic line plot.

    A line plot connects data points with straight lines, useful for showing trends over time or continuous data.
    """
    x = np.linspace(0, 10, 100)  # Generate 100 points from 0 to 10
    y = np.sin(x)  # Sine wave
    plt.figure(figsize=(8, 5))  # Create a figure with specified size
    plt.plot(x, y, label='sin(x)')  # Plot x vs y with a label
    plt.title('Line Plot Example')  # Add title
    plt.xlabel('X-axis')  # Label x-axis
    plt.ylabel('Y-axis')  # Label y-axis
    plt.legend()  # Show legend
    plt.grid(True)  # Add grid
    plt.show()  # Display the plot

def scatter_plot_example():
    """
    Demonstrates a scatter plot.

    Scatter plots show individual data points without connecting lines, ideal for visualizing relationships between two variables.
    """
    x = np.random.rand(50) * 10  # Random x values
    y = np.random.rand(50) * 10  # Random y values
    plt.figure(figsize=(8, 5))
    plt.scatter(x, y, color='red', alpha=0.5)  # Scatter plot with color and transparency
    plt.title('Scatter Plot Example')
    plt.xlabel('X values')
    plt.ylabel('Y values')
    plt.show()

def bar_plot_example():
    """
    Demonstrates a bar plot.

    Bar plots are used to compare categorical data with rectangular bars proportional to the values.
    """
    categories = ['A', 'B', 'C', 'D']
    values = [10, 20, 15, 25]
    plt.figure(figsize=(8, 5))
    plt.bar(categories, values, color='blue')  # Bar plot
    plt.title('Bar Plot Example')
    plt.xlabel('Categories')
    plt.ylabel('Values')
    plt.show()

def histogram_example():
    """
    Demonstrates a histogram.

    Histograms show the distribution of a dataset by dividing data into bins and counting frequencies.
    """
    data = np.random.normal(0, 1, 1000)  # Generate normal distribution data
    plt.figure(figsize=(8, 5))
    plt.hist(data, bins=30, alpha=0.7, color='green')  # Histogram with 30 bins
    plt.title('Histogram Example')
    plt.xlabel('Value')
    plt.ylabel('Frequency')
    plt.show()

def customizing_plots():
    """
    Demonstrates customizing plots with subplots and saving.

    Matplotlib allows multiple plots in one figure (subplots) and saving plots to files.
    """
    x = np.linspace(0, 10, 100)
    y1 = np.sin(x)
    y2 = np.cos(x)

    fig, (ax1, ax2) = plt.subplots(1, 2, figsize=(12, 5))  # 1 row, 2 columns

    ax1.plot(x, y1, 'b-')  # Blue line
    ax1.set_title('Sine Wave')
    ax1.set_xlabel('X')
    ax1.set_ylabel('sin(X)')

    ax2.plot(x, y2, 'r--')  # Red dashed line
    ax2.set_title('Cosine Wave')
    ax2.set_xlabel('X')
    ax2.set_ylabel('cos(X)')

    plt.tight_layout()  # Adjust spacing
    plt.savefig('custom_plot.png')  # Save to file
    plt.show()

# Run examples (uncomment to execute)
# line_plot_example()
# scatter_plot_example()
# bar_plot_example()
# histogram_example()
# customizing_plots()

"""
To run this script, uncomment the function calls at the bottom and execute it with Python.
Ensure Matplotlib and NumPy are installed: pip install matplotlib numpy
"""