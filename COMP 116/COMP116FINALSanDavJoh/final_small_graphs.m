% creates small individual graphs of true/false responses for each color
% 'true' responses defined as selection of "suggested" color
% 'false' indicates selection of either of the other colors

load('testredvar'); 
redresult = result;
load('testgreenvar'); 
greenresult = result;
load('testbluevar');
blueresult = result; % get results from color files

tredresult = [];
fredresult= [];
tblueresult = [];
fblueresult = [];
tgreenresult = [];
fgreenresult = []; % create initial/blank matrices

for j =  1:(length(redresult));
    if redresult(j,4) == 't'
        tredresult = [tredresult; redresult(j,:)]; % counts total true responses for the red suggestion survey
    elseif redresult(j,4) == 'f'
        fredresult = [fredresult; redresult(j,:)]; % counts total false responses for the red suggestion survey
    end
end
for j =  1:(length(blueresult));
    if blueresult(j,4) == 't'
        tblueresult = [tblueresult; blueresult(j,:)]; % counts total true responses for the blue suggestion survey
    elseif blueresult(j,4) == 'f'
        fblueresult = [fblueresult; blueresult(j,:)]; % counts total false responses for the blue suggestion survey
    end
end
for j =  1:(length(greenresult));
    if greenresult(j,4) == 't'
        tgreenresult = [tgreenresult; greenresult(j,:)]; % counts total true responses for the green suggestion survey
    elseif greenresult(j,4) == 'f'
        fgreenresult = [fgreenresult; greenresult(j,:)]; % counts total false responses for the green suggestion survey
    end
end
% Create red subplot
red = [length(tredresult) length(fredresult)];
subplot(3,1,1); bar3(red, 'r'); zlabel('How Many Chose', 'color', 'r', 'fontname', 'arial black'); title('Red Test', 'color', 'r', 'fontname', 'arial black', 'fontsize', 22); set(gca,'YTickLabel', {'True', 'False'} ); 
% Create blue subplot
blue = [length(tblueresult) length(fblueresult)];
subplot(3,1,2); bar3(blue, 'b'); zlabel('How Many Chose', 'color', 'b', 'fontname', 'arial black'); title('Blue Test', 'color', 'b', 'fontname', 'arial black', 'fontsize', 22); set(gca,'YTickLabel', {'True', 'False'} ); 
% Create green subplot
green = [length(tgreenresult) length(fgreenresult)];
subplot(3,1,3); bar3(green, 'g'); zlabel('How Many Chose', 'color', 'g', 'fontname', 'arial black'); title('Green Test', 'color', 'g', 'fontname', 'arial black', 'fontsize', 22); set(gca,'YTickLabel', {'True', 'False'} ); 
