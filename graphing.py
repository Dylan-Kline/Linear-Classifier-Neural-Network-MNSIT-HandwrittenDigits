import matplotlib.pyplot as plt
import pandas as pd

data = pd.read_csv('training_data.csv')

xlimit = len(data)

plt.plot(data['Step'], data['Accuracy'])
plt.xlim(0, xlimit)
plt.ylim(0.0, 1.0)
plt.xlabel('Step Number')
plt.ylabel('Accuracy')
plt.title('Training Progress')
plt.show()