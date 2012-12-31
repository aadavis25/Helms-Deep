% Testx (x=red,blue,green) is designed to accept a users gender and
% age and see if they click the colored square in an image presented
% to them with a certain color ( color is same as function name)suggested
% via capitalization.
%
% Testx utilizes GUI input methods using a three button popup window for
% selecting gender or ending the function and saving the results and a
% typing input for inputting age.
%
clear
load('testgreenvar.mat');
if count==0    
    result = [];
end                 %first time the code runs, establish the result matix
g = 0;
while 1
    ButtonName = questdlg('Please Select Your Gender', 'Gender', 'Male', 'Female', 'Cancel', 'Cancel');
     switch ButtonName
        case 'Male'
            g = 'm';
        case 'Female'
            g = 'f';
        case 'Cancel'
            break
    end
    a = inputdlg('Please Input Your age', 'Age', 1); %GUIs for gender and age
    image = imread('testgreenim.jpg');
    imshow(image);
    axis image;
    [x,y] = ginput(1); %Show the image specific to this script, and accept input
    close;
     %Check if they clicked in the colored square specific to this code
    if ((y >= 225) && (y <= 469)) && ((x >= 665) && (x <= 887));
        result = [result; g cell2mat(a) 't']; %Response registered as true if they select the suggested color (green)
    else 
        result = [result; g cell2mat(a) 'f']; %Response registered as false otherwise
    end         
end
count = count+1;
save('testgreenvar.mat');

