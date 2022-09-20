import sys
nodes_txt=open(sys.argv[1],"r")

nodes=nodes_txt.read().split("\n")
network={}

# Transferring the node into dictionary
for i in nodes:
    (person,connection)=i.split(":")
    connection=connection.split(" ")
    for i in connection:
        if "" in connection:          
            connection.pop()
    network[person]=connection

def ANU(user_name,output):
    user_name_exists=False
    
    for i in network.keys():
        if str(user_name)==i:
            output.write("ERROR: Wrong input type! for 'ANU’! -- This user already exists!!\n")
            user_name_exists=True

    if user_name_exists==False:
        network[str(user_name)]=[]
        output.write(f"User '{user_name}' has been added to the social network successfully\n")
    

def DEU(user_name,output):
    user_name_exists=False
    user_name=str(user_name)

    for i in network.keys():
        if user_name==i:
            network.pop(user_name,None)
            user_name_exists=True

            for i in network.values():
                if user_name in i:
                    i.remove(user_name)
                    
            output.write(f"User '{user_name}' and his/her all relations have been removed successfully.\n")
            break
    
    if user_name_exists==False:
        output.write(f"ERROR: Wrong input type! for 'DEU'!--There is no user named '{user_name}'!!\n")

def ANF(source_user,target_user,output):
    source_user=str(source_user)
    target_user=str(target_user)

    if (source_user not in network.keys()) and (target_user not in network.keys()):
        output.write(f"ERROR: Wrong input type! for 'ANF'! -- No user named '{source_user}' and '{target_user}' found!\n")
    
    elif source_user not in network.keys():
        output.write(f"ERROR: Wrong input type! for 'ANF'! -- No user named '{source_user}' found!!\n")

    elif target_user not in network.keys():
        output.write(f"ERROR: Wrong input type! for 'ANF'! -- No user named '{target_user}' found!!\n")

    elif (target_user in network[source_user]) and (source_user in network[target_user]):
        output.write(f"ERROR: A relation between '{source_user}' and '{target_user}' already exists!!\n")
    
    else:
        network[source_user].append(target_user)
        network[target_user].append(source_user)
        output.write(f"Relation between '{source_user}' and '{target_user}' has been added successfully\n")


def DEF(source_user,target_user,output):
    source_user=str(source_user)
    target_user=str(target_user)

    if (source_user not in network.keys()) and (target_user not in network.keys()):
        output.write(f"ERROR: Wrong input type! for 'DEF'! -- No user named '{source_user}' and '{target_user}' found!\n")
    
    elif source_user not in network.keys():
        output.write(f"ERROR: Wrong input type! for 'DEF'! -- No user named '{source_user}' found!!\n")

    elif target_user not in network.keys():
        output.write(f"ERROR: Wrong input type! for 'DEF'! -- No user named '{target_user}' found!!\n")

    elif (target_user not in network[source_user]) and (source_user not in network[target_user]):
        output.write(f"ERROR: No relation between '{source_user}' and '{target_user}' found!!\n")
    
    else:
        network[source_user].remove(target_user)
        network[target_user].remove(source_user)
        output.write(f"Relation between '{source_user}' and '{target_user}' has been deleted successfully\n")

def CF(user_name,output):
    user_name=str(user_name)
    if user_name not in network.keys():
        output.write(f"ERROR: Wrong input type! for 'CF'! -- No user named '{user_name}' found!\n")
    
    else:
        number_of_Friends=len(network[user_name])
        output.write(f"User '{user_name}' has {number_of_Friends} friends\n")
    


def FPF(user_name,max_Distance,output):
    user_name=str(user_name)
    possible_friend_set=set()

    if max_Distance<1 or max_Distance>3:
        output.write("ERROR: Max_Distance cannot be less than 1 or greater than 3\n")

    if user_name not in network.keys():
        output.write(f"ERROR: Wrong input type! for 'FPF'! -- No user named '{user_name}' found!\n")

    else:

        if max_Distance==1:
            for i in network[user_name]:
                possible_friend_set.add(i)
            possible_friend_list=sorted(list(possible_friend_set))
        
            output.write(f"User '{user_name}' has {len(possible_friend_list)} possible friends when maximum distance is {max_Distance}\n")
            possible_friend_list=str(possible_friend_list).replace("[","{").replace("]","}")
            output.write(f"These possible friends:{possible_friend_list}\n")
        
        elif max_Distance==2:
            possible_friend_list=[]
            possible_p_friend_list=[]
            for i in network[user_name]:
                possible_friend_list.append(i)

            for i in possible_friend_list:
                possible_p_friend_list.append(i)
                for k in network[i]:
                    possible_p_friend_list.append(k)

            while user_name in possible_p_friend_list:
                possible_p_friend_list.remove(user_name)

            possible_p_friend_list=set(possible_p_friend_list)

            possible_p_friend_list=sorted(list(possible_p_friend_list))
            output.write(f"User '{user_name}' has {len(possible_p_friend_list)} possible friends when maximum distance is {max_Distance}\n")
            possible_p_friend_list=str(possible_p_friend_list).replace("[","{").replace("]","}") 
            output.write(f"These possible friends:{possible_p_friend_list}\n")

        elif max_Distance==3:
            possible_friend_list=[]
            possible_p_friend_list=[]
            possible_p_p_friend_list=[]

            for i in network[user_name]:
                possible_friend_list.append(i)
        
            for i in possible_friend_list:
                possible_p_friend_list.append(i)
                for k in network[i]:
                    possible_p_friend_list.append(k)

            for i in possible_p_friend_list:
                possible_p_p_friend_list.append(i)
                for k in network[i]:
                    possible_p_p_friend_list.append(k)

            while user_name in possible_p_p_friend_list:
                possible_p_p_friend_list.remove(user_name)

            possible_p_p_friend_list=set(possible_p_p_friend_list)

            possible_p_p_friend_list=sorted(list(possible_p_p_friend_list))
            output.write(f"User '{user_name}' has {len(possible_p_p_friend_list)} possible friends when maximum distance is {max_Distance}\n")
            possible_p_p_friend_list=str(possible_p_p_friend_list).replace("[","{").replace("]","}") 
            output.write(f"These possible friends:{possible_p_p_friend_list}\n")



def SF(user_name,mutuality_degree,output):
    user_name=str(user_name)
    if user_name not in network.keys():
        output.write(f"Error: Wrong input type! for 'SF'! -- No user named '{user_name}' found!!\n")

    elif mutuality_degree<2 or mutuality_degree>3:

            output.write(f"Error: Mutually Degree cannot be less than 1 or greater than 4\n")

    elif len(network[user_name])<mutuality_degree:
        output.write(f"Error: Friend number of user '{user_name}' cannot be less than Mutually Degree\n")

    else:
        friends_of_user=network[user_name]
        possible_suggested_friends=[]

        for i in friends_of_user:
            for k in network[i]:
                possible_suggested_friends.append(k)
            
        while user_name in possible_suggested_friends:
            possible_suggested_friends.remove(user_name)

        suggested_friends=set()
            
        for i in possible_suggested_friends:
            if possible_suggested_friends.count(i)>=mutuality_degree:
                suggested_friends.add(i)
                    
        output.write(f"Suggestion List for '{user_name}'(when MD is {mutuality_degree}):\n")
        list_suggested_friends=sorted(list(suggested_friends))

        for i in list_suggested_friends:
            if possible_suggested_friends.count(i)==2:
                output.write(f"'{user_name}' has {possible_suggested_friends.count(i)} mutual friends with {i}\n")
        
        for i in list_suggested_friends:
            if possible_suggested_friends.count(i)==3:
                output.write(f"'{user_name}' has {possible_suggested_friends.count(i)} mutual friends with {i}\n")

        
        str_suggested_friends=""
        for i in list_suggested_friends:
            if i==list_suggested_friends[-1]:
                str_suggested_friends=str_suggested_friends + f"'{i}'" 
            else:
                str_suggested_friends=str_suggested_friends + f"'{i}'" +","

        output.write(f"The suggested friends for '{user_name}':{list_suggested_friends}\n".replace("["," ").replace("]"," "))


commands_txt=open(sys.argv[2],"r")
commands=commands_txt.read().split("\n")
output_txt=open("output.txt","w",encoding="utf-8")

for i in commands:
    in_commands=[]
    in_commands.append(i)
    in_commands=in_commands[0].split(" ")
    
    if in_commands[0]=="ANU":
            ANU(in_commands[1],output_txt)

    elif in_commands[0]=="DEU":
            DEU(in_commands[1],output_txt)
        
    elif in_commands[0]=="CF":
        CF(in_commands[1],output_txt)

    try:
        if in_commands[0]=="ANF":
            ANF(in_commands[1],in_commands[2],output_txt)
    except IndexError:
        output_txt.write("ERROR: Wrong command.\n")

    try:
        if in_commands[0]=="DEF":
            DEF(in_commands[1],in_commands[2],output_txt)
    except IndexError:
        output_txt.write("ERROR: Wrong command.\n")

    try:
        if in_commands[0]=="FPF":
            FPF(in_commands[1],int(in_commands[2]),output_txt)
    except IndexError:
        output_txt.write("ERROR: Wrong command.\n")

    try:
        if in_commands[0]=="SF":
            SF(in_commands[1],int(in_commands[2]),output_txt)
    except IndexError:
        output_txt.write("ERROR: Wrong command.\n")

    
nodes_txt.close()
commands_txt.close()
output_txt.close()



