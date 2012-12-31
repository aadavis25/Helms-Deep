function [ CAcoords ] = drawCA( aname, coords )
%Simply plots the CA atoms by testing whether each at has CA in the name
%and adding their respective coordinates to a matrix.
aname1 = int8(aname); % Convert names to numbers to make it simpler
nameCoord = [aname1 coords];

CAcoords = [];
[row,col] = size(nameCoord);
for line = 1:row; 
    test = nameCoord(line,2:3); %Assign the name to an arbitrary variable
    if test == [67 65]; 
    %[67 65] is the number representation of CA, compare that to the variable "test"
    CAcoords = [CAcoords; (nameCoord(line,4)), (nameCoord(line,5)), (nameCoord(line,6))];
    end         %If test == CA, add the coordinates of that line to the matrix
end

%CAgraph = plot3(CAcoords(:,1), CAcoords(:,2), CAcoords(:,3), '-r*'); %plot
end
