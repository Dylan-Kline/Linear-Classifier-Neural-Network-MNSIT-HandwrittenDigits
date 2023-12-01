import matplotlib.pyplot as plt
import pandas as pd

data = pd.read_csv('training_data.csv')

xlimit = len(data)

if 'Step' in data.columns:
    plt.plot(data['Step'], data['Accuracy'])
    plt.xlabel('Step Number')   
elif 'Epoch' in data.columns:
    plt.plot(data['Epoch'], data['Accuracy'])
    plt.xlabel('Epoch')
    
plt.xlim(0, xlimit)
plt.ylim(0.0, 1.0)
plt.ylabel('Accuracy')
plt.title('Training Progress')
plt.show()