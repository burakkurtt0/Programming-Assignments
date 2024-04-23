# -*- coding: utf-8 -*-
"""assignment4.ipynb

Automatically generated by Colaboratory.

Original file is located at
    https://colab.research.google.com/drive/1RczjAvko2crlpowKyXXYX0DO3J4I-KVr
"""

import tensorflow as tf
import cv2
import numpy as np
import sklearn
from sklearn.preprocessing import OneHotEncoder
import os
from tensorflow.keras import Sequential
from tensorflow.keras.layers import Dense, Conv2D, Flatten, Dropout, MaxPooling2D
from tensorflow.keras.optimizers import Adam
from tensorflow.keras.regularizers import l2
from tensorflow.keras.activations import relu, softmax
import matplotlib.pyplot as plt
from sklearn.metrics import confusion_matrix

images = []
labels = []
path = "Dataset/indoorCVPR_09/Images/"
for i in os.listdir(path):
    file_loc = path + i

    for j in os.listdir(file_loc):
        try:
            img = cv2.imread(file_loc + "/" + j)
            img = cv2.resize(img, (128, 128))
            images.append(img)
            labels.append(i)
        except:
            print("Error in " + file_loc + "/" + j)

images = np.array(images)
labels = np.array(labels)
ohe = OneHotEncoder()
labels = ohe.fit_transform(labels.reshape(-1,1)).toarray()
print(images.shape)
print(labels.shape)

from sklearn.model_selection import train_test_split

images_train, images_test, labels_train, labels_test = train_test_split(images, labels, test_size=0.2)


print("Train set shapes:")
print("Images:", images_train.shape)
print("Labels:", labels_train.shape)
print()
print("Test set shapes:")
print("Images:", images_test.shape)
print("Labels:", labels_test.shape)

def createModel(learning_rate,batch_size,epochs = 30):
    model = Sequential()
    model.add(Conv2D(16, (3,3), activation='tanh', input_shape=(128,128,3)))
    model.add(MaxPooling2D(pool_size=(2,2),strides=2))
    model.add(Conv2D(32,(3,3),activation="tanh"))
    model.add(MaxPooling2D(pool_size=(2,2),strides=2))
    model.add(Conv2D(64,(3,3),activation="tanh"))
    model.add(MaxPooling2D(pool_size=(2,2),strides=2))
    model.add(Conv2D(128,(3,3),activation="tanh"))
    model.add(MaxPooling2D(pool_size=(2,2),strides=2))
    model.add(Flatten())


    model.add(Dense(3136, activation='relu'))
    model.add(Dense(1024, activation='relu'))
    model.add(Dense(512, activation='relu'))
    model.add(Dense(256, activation='relu'))
    model.add(Dense(67, activation='softmax'))

    model.compile(optimizer=Adam(learning_rate=learning_rate), loss='categorical_crossentropy', metrics=['accuracy'])
    hist = model.fit(images_train, labels_train, batch_size=batch_size, epochs=epochs, validation_split=0.2)
    return hist,model

def plotResult(hist,lr,batch_size):

    fig = plt.figure(figsize=(10,10))
    plt.subplot(2,1,1)
    plt.plot(hist.history['accuracy'],label = "Training Accuracy")
    plt.plot(hist.history['val_accuracy'],label = "Validation Accuracy")
    plt.title("Learning Rate = " + str(lr) + ", Batch Size = " + str(batch_size))
    plt.legend()

    plt.subplot(2,1,2)
    plt.plot(hist.history['loss'],label = "Training Loss")
    plt.plot(hist.history['val_loss'],label = "Validation Loss")
    plt.title("Learning Rate = " + str(lr) + ", Batch Size = " + str(batch_size))
    plt.legend()

    plt.show()

def plotConfusionMatrix(model,lr,batch_size):
    y_pred = model.predict(images_test)
    y_pred = np.argmax(y_pred,axis = 1)
    y_true = np.argmax(labels_test,axis = 1)
    cm = confusion_matrix(y_true,y_pred)
    plt.figure(figsize=(10,10))
    plt.imshow(cm,cmap = "Blues")
    plt.title("Learning Rate = " + str(lr) + ", Batch Size = " + str(batch_size))
    plt.colorbar()
    plt.show()

exp_batch = [16,32]
exp_lr = [0.001,0.0001,0.00005]

hist1,model1 = createModel(learning_rate=exp_lr[0],batch_size=exp_batch[0])
plotResult(hist1,exp_lr[0],exp_batch[0])
plotConfusionMatrix(model1,exp_lr[0],exp_batch[0])

hist2,model2 = createModel(learning_rate=exp_lr[1],batch_size=exp_batch[0])
plotResult(hist2,exp_lr[1],exp_batch[0])
plotConfusionMatrix(model2,exp_lr[1],exp_batch[0])

hist3,model3 = createModel(learning_rate=exp_lr[2],batch_size=exp_batch[0])
plotResult(hist3,exp_lr[2],exp_batch[0])
plotConfusionMatrix(model3,exp_lr[2],exp_batch[0])

hist4,model4 = createModel(learning_rate=exp_lr[0],batch_size=exp_batch[1])
plotResult(hist4,exp_lr[0],exp_batch[1])
plotConfusionMatrix(model4,exp_lr[0],exp_batch[1])

hist5,model5 = createModel(learning_rate=exp_lr[1],batch_size=exp_batch[1])
plotResult(hist5,exp_lr[1],exp_batch[1])
plotConfusionMatrix(model5,exp_lr[1],exp_batch[1])

hist6,model6 = createModel(learning_rate=exp_lr[2],batch_size=exp_batch[1])
plotResult(hist6,exp_lr[2],exp_batch[1])
plotConfusionMatrix(model6,exp_lr[2],exp_batch[1])

"""Best Setting --> Learning Rate = 0.001 , Batch Size = 16

## Dropout Experiments
"""

def createModel_Dropout(learning_rate,batch_size,epochs = 30,dropout_rate = 0.2):
    model = Sequential()
    model.add(Conv2D(16, (3,3), activation='tanh', input_shape=(128,128,3)))
    model.add(MaxPooling2D(pool_size=(2,2),strides=2))
    model.add(Conv2D(32,(3,3),activation="tanh"))
    model.add(MaxPooling2D(pool_size=(2,2),strides=2))
    model.add(Conv2D(64,(3,3),activation="tanh"))
    model.add(MaxPooling2D(pool_size=(2,2),strides=2))
    model.add(Conv2D(128,(3,3),activation="tanh"))
    model.add(MaxPooling2D(pool_size=(2,2),strides=2))
    model.add(Flatten())


    model.add(Dense(3136, activation='relu'))
    model.add(Dropout(dropout_rate))
    model.add(Dense(1024, activation='relu'))
    model.add(Dropout(dropout_rate))
    model.add(Dense(512, activation='relu'))
    model.add(Dense(256, activation='relu'))
    model.add(Dense(67, activation='softmax'))

    model.compile(optimizer=Adam(learning_rate=learning_rate), loss='categorical_crossentropy', metrics=['accuracy'])
    hist = model.fit(images_train, labels_train, batch_size=batch_size, epochs=epochs, validation_split=0.2)
    return hist,model

dropout_values = [0.2,0.3,0.4,0.5]
lr = 0.001
batch_size = 16
labels_test = np.argmax(labels_test,axis=1).reshape(-1,1)

hist1,model1 = createModel_Dropout(learning_rate=lr,batch_size=batch_size,epochs=30,dropout_rate=dropout_values[0])
plotResult(hist1,lr,batch_size)
plotConfusionMatrix(model1,lr,batch_size)

hist2,model2 = createModel_Dropout(learning_rate=lr,batch_size=batch_size,epochs=30,dropout_rate=dropout_values[1])
plotResult(hist2,lr,batch_size)
plotConfusionMatrix(model2,lr,batch_size)

hist3,model3 = createModel_Dropout(learning_rate=lr,batch_size=batch_size,epochs=30,dropout_rate=dropout_values[2])
plotResult(hist3,lr,batch_size)
plotConfusionMatrix(model3,lr,batch_size)

hist4,model4 = createModel_Dropout(learning_rate=lr,batch_size=batch_size,epochs=30,dropout_rate=dropout_values[3])
plotResult(hist4,lr,batch_size)
plotConfusionMatrix(model4,lr,batch_size)

"""## PART 2"""

import tensorflow as tf
from tensorflow.keras.applications import VGG16

vgg16_model = VGG16(weights='imagenet', include_top=False,classes = 67, input_shape=(128,128,3))
print(vgg16_model.summary())

# Freeze all the layers
for layer in vgg16_model.layers:
    layer.trainable = False

# Check the trainable status of the individual layers
for layer in vgg16_model.layers:
    print(layer, layer.trainable)

def createVGGModel(lr,batch_size):
    model = Sequential()
    model.add(vgg16_model)
    model.add(Flatten())
    model.add(Dense(2048, activation='relu'))
    model.add(Dense(512, activation='relu'))
    model.add(Dense(67, activation='softmax'))

    model.compile(optimizer=Adam(learning_rate=lr), loss='categorical_crossentropy', metrics=['accuracy'])
    hist = model.fit(images_train, labels_train, batch_size=batch_size, epochs=20, validation_split=0.2)
    return hist,model

lr = [0.001,0.0001,0.00001]
batch_size = [16,32]

hist1,vgg1 = createVGGModel(lr[0],batch_size[0])
plotResult(hist1,lr[0],batch_size[0])
plotConfusionMatrix(vgg1,lr[0],batch_size[0])

hist2,vgg2 = createVGGModel(lr[1],batch_size[0])
plotResult(hist2,lr[1],batch_size[0])
plotConfusionMatrix(vgg2,lr[1],batch_size[0])

hist3,vgg3 = createVGGModel(lr[2],batch_size[0])
plotResult(hist3,lr[2],batch_size[0])
plotConfusionMatrix(vgg3,lr[2],batch_size[0])

hist4,vgg4 = createVGGModel(lr[0],batch_size[1])
plotResult(hist4,lr[0],batch_size[1])
plotConfusionMatrix(vgg4,lr[0],batch_size[1])

hist5,vgg5 = createVGGModel(lr[1],batch_size[1])
plotResult(hist5,lr[1],batch_size[1])
plotConfusionMatrix(vgg5,lr[1],batch_size[1])

hist6,vgg6 = createVGGModel(lr[2],batch_size[1])
plotResult(hist6,lr[2],batch_size[1])
plotConfusionMatrix(vgg6,lr[2],batch_size[1])

vgg16_model = VGG16(weights='imagenet', include_top=False,classes = 67, input_shape=(128,128,3))


print(vgg16_model.summary())


# Freeze all the layers
for layer in vgg16_model.layers[:-3]:
    layer.trainable = False

# Check the trainable status of the individual layers
for layer in vgg16_model.layers:
    print(layer, layer.trainable)

histc_1,vggc_1 = createVGGModel(lr[0],batch_size[0])
plotResult(histc_1,lr[0],batch_size[0])
plotConfusionMatrix(vggc_1,lr[0],batch_size[0])

histc_2,vggc_2 = createVGGModel(lr[1],batch_size[0])
plotResult(histc_2,lr[1],batch_size[0])
plotConfusionMatrix(vggc_2,lr[1],batch_size[0])

histc_3,vggc_3 = createVGGModel(lr[2],batch_size[0])
plotResult(histc_3,lr[2],batch_size[0])
plotConfusionMatrix(vggc_3,lr[2],batch_size[0])

histc_4,vggc_4 = createVGGModel(lr[0],batch_size[1])
plotResult(histc_4,lr[0],batch_size[1])
plotConfusionMatrix(vggc_4,lr[0],batch_size[1])

histc_5,vggc_5 = createVGGModel(lr[1],batch_size[1])
plotResult(histc_5,lr[1],batch_size[1])
plotConfusionMatrix(vggc_5,lr[1],batch_size[1])

histc_6,vggc_6 = createVGGModel(lr[2],batch_size[1])
plotResult(histc_6,lr[2],batch_size[1])
plotConfusionMatrix(vggc_6,lr[2],batch_size[1])