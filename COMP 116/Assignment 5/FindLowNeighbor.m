function [ rowoff, coloff ] = FindLowNeighbor( map )
%FindLowNeighbor takes alkl the pixels/point in a matrix and identifies the
%lowest point adjacent to each point. Two matrices are created (rowoff and
%coloff) identifying where in the row and column space the corrsponding
%lowest neighbor is.

[r,c] = size(map);
rowoff = zeros(size(map));
coloff = zeros(size(map));
for i = 2:(r-1);
    for j = 2:(c-1); %For the entire marix "map"
        low = map(i,j); %establish a baseline lowest value for comparison
        for k = i-1:i+1;
            for l = j-1:j+1; %For all 8 neighbors around the tested point
               if  map(k,l) < low;
                   low = map(k,l);
                   rowoff (i,j) = k-i;
                   coloff (i,j) = l-j; %If the tested point (k,l) is lower than our tested point (i,j), fill rowoff and coloff with the coordinates of that pont relative to the tested point
               end
            end
        end
    end
end
end