% I pledge tht this work is my own and I recieved no unauthorized
% assistance on the following assignment.

%% Assignment 2 - Plotting
% Aaron Davis

%% Question 1 Plot a line graph of depths for both lakes. (Is there an obvious yearly cycle?)
x1 = (1/12:1/12:23);
plot (x1, jordepth, x1, falldept, 'g')
title('Depth of Fall and Jordan Lakes monthly 1985 - 2007')
legend ('Jordan Lake', 'Fall Lake')
xlabel('Months')
ylabel ('Depth in Inches')
% There is a slight pattern, but nothing very obvious or substantial.

%% Question 2 The targets for Jordan and Falls lakes are 216ft and 251.5ft,respectively. For how many months was each lake over its target?

% Jordan Lake was over it's target depth 178 months and Fall Lake was over
% it's target 71 months.

%% Question 3  Plot the rain in August as a line graph over years for both lakes.

x2 = [1:1:23];
plot (x2,fallaugust, '-r*', x2, jordaugust, '-b+')
legend ('Fall Lake','Jordan Lake')
title ('Depth of Fall and Jordan Lakes in August 1985 - 12007')
ylabel ('Depth in Inches')
xlabel ('Years after 1985')
legend ('Fall Lake', 'Jordan Lake')

%% Question 4 Compute the average height that Falls Lake is above its target for each month over the 23 years from 1985-2007, and display as bar chart with a bar for each month. Plot the line for 2007 in red on top of this bar chart.

x3 = 1:1:12;
bar (x3, y')
hold  on
plot (x3, fall2007, '-r')
legend('Average of Each Month for all 23 Years', 'Average for 2007')
title(' Amount Above or Below Average Depth for Fall Lake 1985 - 2007')
xlabel('Month')
ylabel('Amount Above or Below in Inches')
hold off

%% Question 5 Determine how many days had more than 1 in of precipitation at the two sites in hawrain, and how many days had less than ¼ in.

% Each site had 7 days with more than 1 in. of rain. Site 1 had 330 days
% with less than a quarter of an inch of rain. Site 2 had 324 days with
% less than a quarter of an inch of rain.

%% Question 6 Plot line graphs showing the cumulative amount of rain over the past year at both sites. Which of the two locations (1 or 2) received the most rain?

x6 = 1:1:365;
plot (x6, cumsum(hawrain1), x6, cumsum(hawrain2), 'r')
legend ('Hawrain site 1', 'hawrain site 2')
xlabel('Days After 29 Aug 07 - 28 Aug 08')
ylabel ('Cumulative In. Of Rain')
title('Cumulative Amount Of Rain At Hawrain sites 1 & 2')
%Hawrain site 2 had the most cumulative amount of rain.

%% Question 7 Determine the lowest height for each gauge, and create a matrix or vectors of adjusted heights by subtracting the corresponding lowest heights. Plot these adjust heights as a line graph.

x7= 1:1:365;
plot (x7, hawgage11low, x7, hawgage21low, '--r', x7, hawgage31low, 'k', x7, hawgage41low, '--g')
legend( 'Site 1 - Jordan Lake', 'Site 2- Haw River', 'Site 3 - Below Jordan Dam', 'Site 4 - Bynum Tiver')
xlabel('Days Over A 1 Year Period')
ylabel('Height In Inches') 
title('Average Height for 4  Hawgage sites (with corresponding lowest value subtracted from all heights)')
%% Question  8 Determine the maximum increase and maximum decrease in height from one day to the next for each of the four gauges in hawgage.

   % Hawgague site 1, Jordan lake, had a maximum increase of 1.16 inches
   % and a maximum decrease of -.47 inches. Site 2, Haw River, saw a
   % maximum increase of 7.15 inches and a maximum decrease of -6.22
   % inches. Site 3, Below the Jordan Lake Dam, saw a maximum increase of
   % 2.57 inches and a maximum decrease od -1.56 inches. Site 4, Bynum
   % River, saw a maximum increase of 4.37 inches and a maximum decrease of
   % -2.53 inches.
   
   
   % I had a little trouble as far as the creation of so many variables
   % goes, but after that, the plotting wasn't too challenging. 