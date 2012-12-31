%% Assignment 6
% Aaron Davis
% I pledge that I did this work without any unauthorized assistance.
%%
% 1, 2, 3 in folder
%% 4. Read in 7hvp.pdb
[anum1, aname1, resno1, coords1] = readPDBFile('7HVP.pdb');
%% 5. Plot CA backbone of 7hvp.pdb
[CAcoords] = drawCA(aname1, coords1);
plot3(CAcoords(:,1), CAcoords(:,2), CAcoords(:,3), '-r*');
%% 6. Display how many hydrogen bonds you found in 7hvp.pdb.
bonds1 = hbonds(anum1, aname1, resno1, coords1);
%There were 138 bonds.
%% 7. Read in 1gfl.pdb.
[anum2, aname2, resno2, coords2] = readPDBFile('1GFL.pdb');
%% 8. Plot CA backone of 1gfl.pdb
[CAcoords] = drawCA(aname2, coords2);
plot3(CAcoords(:,1), CAcoords(:,2), CAcoords(:,3), '-r*');
%% 9. Display how many hydrogen bonds you found in 1gfl.pdb.
bonds2 = hbonds(anum2, aname2, resno2, coords2);
%There were 423 bonds.