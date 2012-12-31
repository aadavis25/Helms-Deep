function [ pitList ] = findPits( map )
%findPits uses the rowoff and coloff matrices found in findLowNeighbor and
%locates points whos row offsets and column offsets are both 0.

[r,c] = size(map);
[rowoff, coloff] = FindLowNeighbor(map);
pitList = [];%establish pitList
for g = 2:(r-1)
    for h = 2:(c-1);
        if rowoff(g,h)==0 && coloff(g,h)==0
            pitList = [pitList; g h]; %Walk down rows and columns and 
                                              %find where rowoff and coloff 
                                              %both equal 0
        end
    end
end

end

