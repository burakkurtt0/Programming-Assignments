import sys

colors=["X","F","D","O","P","R","Y","W","G","B"]
#matching colors with their values.
colors_w_values=dict()
z=0
for i in colors:
    colors_w_values[i]=z
    z+=1

input_txt=open(sys.argv[1],"r+")
color_map=[]
for i in input_txt.read().split("\n"):
    color_map.append(i.split(" "))

#convert map to string, so I can print it out as rows and columns
def color_map_to_string(colormap):
    str_line=""
    for i in colormap:
        if i[-1]=="\n":
            pass
        else:
            i.append("\n")
        for k in i:
            str_line+=" "+k
    print(" ")
    print(str_line)

length_of_Rows=len(color_map)

#checking the rows whether there is an empty row.
def checkRows(colormap,length):
    x=0
    i=0
    while True:
        if i>len(colormap)-1:
            i=0


        if colormap[i].count(" ")==len(colormap[i])-1 or colormap[i].count(" ")==len(colormap[i]):
            colormap.pop(i) 

        if i>=len(colormap)-1:
            i=0
        
        if x==length**3:
            break
        x+=1
        i+=1
        
    return colormap
        
def checkColumns(colormap,length):
  
    columns_list=[]
    for k in range(len(colormap[0])):
        columns_list.append([colormap[i][k] for i in range(len(colormap))])
        
    if "\n" in columns_list[-1]:
        columns_list.pop()
  
    #checking for shifting to downward
    for i in range(len(colormap)):
        for k in range(len(colormap[0])):
            l=0
            while l<length**4:
                try:
                    if colormap[i+1][k]==" ":
                        colormap[i+1][k]=colormap[i][k]
                        colormap[i][k]=" "

                except:
                    pass
                l+=1

                if i==len(colormap)-1:
                    i=0
                
    #checking for any empty column
    i=0
    while True:
       
        if columns_list[i].count(" ")==len(columns_list[i]):
            
            columns_list.pop(i)
            for k in range(len(colormap)):
                try:
                    colormap[k].pop(i)
                    
                except:
                    pass
        else:
            i+=1
        if i==len(columns_list)-1:
            break
       
        

def update_maps(colormap,length):
    i=0
    while i<length**2:
        checkColumns(colormap,length)
        colormap=checkRows(colormap,length)
        checkColumns(colormap,length)
        i+=1
    return colormap

score=0

def FindBomb(score,colormap,co1,co2):
  
            colormap[co1][co2]=" "
            for i in range(len(colormap[co1])):
                try:
                    score+=colors_w_values[colormap[co1][i]]
                    if colormap[co1][i]=="X":
                        score=FindBomb(score,colormap,co1,i)
                except IndexError:
                    pass            
                except KeyError:
                    pass
                try:
                    colormap[co1][i]=" "
                except IndexError:
                    pass
            
            
            for i in range(len(colormap)):
                try:
                    score+=colors_w_values[colormap[i][co2]]
                    if colormap[i][co2]=="X":
                        score=FindBomb(score,colormap,i,co2)
                except IndexError:
                    pass
                except KeyError:
                    pass
                colormap[i][co2]=" "
            
           
            return score

def Find_nearby_colors(score,color,colormap,co1,co2):
    try:
        score+=colors_w_values[color]
    except KeyError:
        pass
    try:
        try:
            if color==colormap[co1+1][co2]:
                color1=colormap[co1+1][co2]
                colormap[co1][co2]=" "
                score=Find_nearby_colors(score,color1,colormap,co1+1,co2)   
        except IndexError:
            colormap[co1][co2]=" "
           
        try:
            if color==colormap[co1-1][co2]:
                color2=colormap[co1-1][co2]
                colormap[co1][co2]=" "
                score=Find_nearby_colors(score,color2,colormap,co1-1,co2)

        except IndexError:
            colormap[co1][co2]=" "
           
        try:
            if color==colormap[co1][co2+1]:
                color3=colormap[co1][co2+1]
                colormap[co1][co2]=" "
                score=Find_nearby_colors(score,color3,colormap,co1,co2+1)
                
        except IndexError:
            colormap[co1][co2]=" "
            
        try:
            if color==colormap[co1][co2-1]:
                color4=colormap[co1][co2-1]
                colormap[co1][co2]=" "
                score=Find_nearby_colors(score,color4,colormap,co1,co2-1)
                
        except IndexError:
            colormap[co1][co2]=" "
        colormap[co1][co2]=" "
        
        return score
    except:
        pass

def Gameover(colormap):
    Game_over_list=[]
    for i in range(len(colormap)):
        for j in range(len(colormap[0])-1):
            if colormap[i][j]==" " or colormap[i][j]=="\n":
                pass
            else:
                if colormap[i][j]=="X":
                        Game_over_list.append("F")
                
                if i==0 and j==0:
                    if len(colormap)==1:
                        if colormap[i][j]!=colormap[i][j+1]:
                            Game_over_list.append("T")
                        else:
                            Game_over_list.append("F")
                    else:
                        if colormap[i][j]!=colormap[i+1][j] and colormap[i][j]!=colormap[i][j+1]:
                            Game_over_list.append("T")
                        else:
                            Game_over_list.append("F")
                
                elif i==0:
                    if len(colormap)==1:
                        if colormap[i][j]!=colormap[i][j+1]:
                            Game_over_list.append("T")
                        else:
                            Game_over_list.append("F")
                    else:
                        if colormap[i][j]!=colormap[i+1][j] and colormap[i][j]!=colormap[i][j+1]:
                            Game_over_list.append("T")
                        else:
                            Game_over_list.append("F")

                elif i==len(colormap)-1 and j==0:
                    if colormap[i][j]!=colormap[i-1][j] and colormap[i][j]!=colormap[i][j+1]:
                        Game_over_list.append("T")
                    else:
                        Game_over_list.append("F")
                
                elif j==0:
                    if colormap[i][j]!=colormap[i+1][j] and colormap[i][j]!=colormap[i-1][j] and colormap[i][j]!=colormap[i][j+1]:
                        Game_over_list.append("T")
                    else:
                        Game_over_list.append("F")
                    
                elif i==len(colormap)-1 :
                    if colormap[i][j]!=colormap[i-1][j] and colormap[i][j]!=colormap[i][j+1] and colormap[i][j]!=colormap[i][j-1]:
                        Game_over_list.append("T")
                    else:
                        Game_over_list.append("F")
                
                else:
                    if colormap[i][j]!=colormap[i+1][j] and colormap[i][j]!=colormap[i-1][j] and colormap[i][j]!=colormap[i][j+1] and colormap[i][j]!=colormap[i][j-1]:
                        Game_over_list.append("T")
                    else:
                        Game_over_list.append("F")
                  

    if "F" in Game_over_list:
        return False
    else:
        return True

def Process_on_map(score,color,colormap,co1,co2,length):
    temp_score=Find_nearby_colors(score,color,colormap,co1,co2)
    colormap=update_maps(colormap,length=length_of_Rows)     
    color_map_to_string(colormap)                     
    return temp_score

def PlayGame(colormap):
    color_map_to_string(color_map)
    print("Your score is 0\n")
    High_Score=0
    while True:
        while True:
            coordinates=input("Please enter a row and column number: ")
            coordinates=coordinates.split(" ")
            if len(coordinates)!=2:
                print("Please enter just 2 numbers with a space between them.\n")
                break
            else:
                try:
                    co1=int(coordinates[0])
                    co2=int(coordinates[1])
                    color=colormap[co1][co2]
                    
                    if color==" " or color=="\n":
                        print("Please enter a valid size!\n")
                        break
                     
                except:
                    print("Please enter a valid size!\n")
                    break
            try:
                if color=="X":
                    temp_score=FindBomb(score,colormap,co1,co2)
                    checkRows(colormap,length=length_of_Rows)
                    checkColumns(colormap,length_of_Rows)
                    colormap=update_maps(colormap,length=length_of_Rows)
                    High_Score+=temp_score
                    color_map_to_string(colormap)
                    print(f"Your score is: {High_Score}\n")

                else:
                    if co1==0 and co2==0:
                        if color!=colormap[co1+1][co2]  and color!=colormap[co1][co2+1]:
                            color_map_to_string(colormap)
                            break
                        else:
                            temp_score=Process_on_map(score,color,colormap,co1,co2,length_of_Rows)
                            High_Score+=temp_score
                            print(f"Your score is: {High_Score}\n")
                        
                    
                    elif co1==0:
                        if color!=colormap[co1+1][co2]  and color!=colormap[co1][co2+1] and color!=colormap[co1][co2-1]:
                            color_map_to_string(colormap)
                            break
                        else:
                            temp_score=Process_on_map(score,color,colormap,co1,co2,length_of_Rows)
                            High_Score+=temp_score
                            print(f"Your score is: {High_Score}\n")
                    
                    elif co1==len(colormap)-1 and co2==0:
                        if  color!=colormap[co1-1][co2] and color!=colormap[co1][co2+1]:
                            color_map_to_string(colormap)
                            break
                        else:
                            temp_score=Process_on_map(score,color,colormap,co1,co2,length_of_Rows)
                            High_Score+=temp_score
                            print(f"Your score is: {High_Score}\n")

                    elif co2==0:
                        if color!=colormap[co1+1][co2] and color!=colormap[co1-1][co2] and color!=colormap[co1][co2+1]:
                            color_map_to_string(colormap)
                            break
                        else:
                            temp_score=Process_on_map(score,color,colormap,co1,co2,length_of_Rows)
                            High_Score+=temp_score
                            print(f"Your score is: {High_Score}\n")

                    elif co1==len(colormap)-1:
                        if  color!=colormap[co1-1][co2] and color!=colormap[co1][co2+1] and color!=colormap[co1][co2-1]:
                            color_map_to_string(colormap)
                            break
                        else:
                            temp_score=Process_on_map(score,color,colormap,co1,co2,length_of_Rows)
                            High_Score+=temp_score
                            print(f"Your score is: {High_Score}\n")
                    
                    else:
                        if color!=colormap[co1+1][co2] and color!=colormap[co1-1][co2] and color!=colormap[co1][co2+1] and color!=colormap[co1][co2-1]:
                            color_map_to_string(colormap)
                            break
                            
                        else:
                            temp_score=Process_on_map(score,color,colormap,co1,co2,length=length_of_Rows)
                            High_Score+=temp_score
                            print(f"Your score is: {High_Score}\n")

                break
            except UnboundLocalError:
                pass
     
        Game_Status=Gameover(colormap)
        if Game_Status:
            print("Game over!")
            break
       
PlayGame(color_map)


