function [ hBondPairs ] = hBonds ( anum, aname, resno, coords )
%hBonds find the hydrogen bonds by testing for 3 different conditions: atom
%names, distances apart, and residue sequence numbers.
tic
hBondPairs = [];
for i = 1:length(anum)-1
    for j = i:length(anum) %Walk down the values, but testing each pair only once
        if (strcmp(aname(i,2), 'O') || strcmp(aname(i,2), 'N')) &&... %test if i is N or O
           (strcmp(aname(j,2), 'O') || strcmp(aname(j,2), 'N')) &&... %test if j is N or O
           (~strcmp(aname(i,:), aname(j,:)));  %make sure they're not both N or O
           d = ((coords(i,1) - coords(j,1))^2 + (coords(i,2) - coords(j,2))^2 ...
           + (coords(i,3) - coords(j,3))^2)^.5; %distance between i and j
           if (d > 2.6) && (d < 3.2) 
               resdiffi = str2double(resno(i,:));
               resdiffj = str2double(resno(j,:)); % convert residual sequences to integers
               if abs(resdiffi - resdiffj) >= 2 %test if residual numbers differ by more than 2
                   hBondPairs = [hBondPairs; anum(i) anum(j)];
               end
           end
        end
    end
end
toc
end

