function [ dropPath ] = calcPath( map )
%CalcPath plots the path of a raindrop by using the twomatrices from
%FindLowNeighbor and moving the drop toward the lowest neighbors.

imagesc(map); 
axis equal; 
colormap (gray); %display the map
[rowmap, colmap] = size(map);
[col, row] = ginput(1); %user inputs startpoint
row = round(row);
col = round(col);
[rowoff, coloff] = FindLowNeighbor(map); %Use FindLowNeighbor to get the two matrices
path = [row, col];%Path will be the matrix that gets plotted
steps = size(path);%Steps will be part of the while statement
while (steps(1,:) < 1000) & ((col > 1) && (col < colmap) && (row < rowmap) && (row > 1))
           if (rowoff(row,col) == 0) && (coloff(row,col)==0)
                break
           end
           row = row + rowoff(row,col);
           col = col + coloff(row,col); %Add the coordinates of the lowest neighbor        
           path = [path; row col];%Add the coordinates of the lowest neighbor to path to be plotted          
end
hold on
plot(path(:,2),path(:,1),'b');%plot the path
end