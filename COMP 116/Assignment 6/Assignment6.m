%% Assignment 6
% Aaron Davis
% I pledge that I did this work without any unauthorized assistance.
%%
% 1, 2, 3 in folder
%% 4. Read in 7hvp.pdb
[anum1, aname1, resno1, coords1] = readPDBFile('7HVP.pdb');
[atoms1, ~] = size(anum1);
atoms1
%% 5. Plot CA backbone of 7hvp.pdb
CAcoords1 = drawCA(aname1, coords1);
plot3(CAcoords1(:,1), CAcoords1(:,2), CAcoords1(:,3), '-r*');
%% 6. Display how many hydrogen bonds you found in 7hvp.pdb.
bonds1 = hBonds(anum1, aname1, resno1, coords1);
[num1, ~] = size(bonds1);
num1
%There were 138 bonds.
%% 7. Read in 1gfl.pdb.
[anum2, aname2, resno2, coords2] = readPDBFile('1GFL.pdb');
[atoms2, ~] = size(anum2);
atoms2
%% 8. Plot CA backone of 1gfl.pdb
CAcoords2 = drawCA(aname2, coords2);
plot3(CAcoords2(:,1), CAcoords2(:,2), CAcoords2(:,3), '-r*');
%% 9. Display how many hydrogen bonds you found in 1gfl.pdb.
bonds2 = hBonds(anum2, aname2, resno2, coords2);
[num2, ~] = size(bonds2);
num2
%There were 423 bonds.