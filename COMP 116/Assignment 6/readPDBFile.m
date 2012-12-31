function [ anum, aname, resno, coords ] = readPDBFile( infile )
%readPDBFile walks trhough all the lines of a plain text file and finds the
%atoms, adds their coordinates, residual numbers, names, and numers to the
%four matrices.
anum = [];
aname = [];
resno = [];
coords = []; % establish empty matrices
FID = fopen(infile); %read in file identifier
while (~feof(FID));                 % While there is still data,
    line = fgetl(FID);              % go line by line
    test = line(1:6);               % and test if the name (line 1:6)
    if strcmp(test, 'ATOM  ') == 1; % is 'ATOM  '
        anum = [anum; line(7:11)];  
        aname = upper([aname; line(13:16)]);
        resno = [resno; line(23:26)];
        coords = [coords; str2num(line(31:38)) str2num(line(39:46)) str2num(line (47:54))]; 
        %Add the data to the matrices if above is true
    end
end
end

