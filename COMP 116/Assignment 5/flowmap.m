function [ mapflow ] = flowmap(map,threshhold)
%Flowmap assigns each pixel in "map" one unit of flow, and by walking
%through the highest to lowest points in "map," adds each units of flow to
%each subsequent lowest neighbor

[~, index] = sort(-(map(:))); %Index is the list of values, highest to lowest, in map.
[rows, cols] = size(map);
indexR = mod(index, rows);
for i = 1:size(indexR)
    if (indexR(i) == 0)
    indexR(i) = rows;
    end
end
indexC = ((index - indexR) / cols) + 1; %IndexC and indexR are the corresponding indices to the values in map.
[indrows, ~] = size(index);
[rowoff, coloff] = FindLowNeighbor(map);
flow=ones(rows, cols); %establish the lowest neighor matrices and establish the flow matrix
for j=1:indrows
    flow((indexR(j) + rowoff(indexR(j), indexC(j))), (indexC(j) +... 
    coloff(indexR(j), indexC(j)))) =... 
    flow((indexR(j) + rowoff(indexR(j), indexC(j))), (indexC(j) +... 
    coloff(indexR(j), indexC(j)))) + flow(indexR(j), indexC(j)); %make the flow of the lowest neighbor of the current point its flow plus the flow of the current point.
end
mapflow = flow>threshhold; %We want the flows that are greater than a certain value, threshhold
mapflow = flipud(mapflow);
end
