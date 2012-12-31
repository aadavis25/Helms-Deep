%% Assingment 3
% Aaron Davis

% I pledge that I have not recieved any unauthorized help on this assignment
%and the work shown herein is mine.

%% Part 1 (Book Problem)

force = [cos(45*(180/pi)), 1,0,0,0,0,0,0,0; -cos(45*(180/pi)),0,0,1,cos(48.81*(180/pi)),0,0,0,0;-sin(45*(180/pi)),0,-1,0,-sin(48.81*(180/pi)),0,0,0,0;0,0,0,-1,0,0,0,cos(48.81*(180/pi)),0;0,0,0,0,0,0,-1,-sin(48.81*(180/pi)),0;0,0,-cos(48.81*180/pi),0,0,-1,0,0,1;0,0,0,0,sin(48.81*(180/pi)),0,1,0,0;0,0,0,0,0,0,0,sin(48.81*(180/pi)),0;0,0,0,0,0,0,0,-cos(48.81*(180/pi)),-1];
forceout = [0;0;1000;0;500;0;4000;-1107.14;0]; %Solutions to the equations
trussforce = force*(forceout);

%F1 = -5.7741
%F2 = -3.4172
%F3 = 0.2615
%F4 = -1.6552
%F5 = 6.1025
%F6 = 1.4378
%F7 = 0.6071
%F8 = -1.9913
%F9 = 1.6552

%% Part 2 (Assignment)

clark = imread('clark.jpg');
imagesc(clark)
axis image
hold on
[x,y] = ginput(10);
plot (x,y, '.y')
circle = [zeros(10,1),zeros(10,1),x,y,ones(10,1);]\[(-x.^2)-(y.^2)];
Dc = circle(3,:) ;
Fc = circle(4,:);
Gc = circle(5,:); %Coefficiants for the circle (Dc-Gc)
oval = [zeros(10,1),(y.^2),x,y,ones(10,1);]\[(-x.^2)];
Co = oval (2,:);
Do = oval (3,:);
Fo = oval (4,:);
Go = oval(5,:); %Coefficiants for the oval (Co-Go)
ellipse = [x.*y,(y.^2),x,y,ones(10,1);]\[(-x.^2)];
Be = ellipse (1,:);
Ce = ellipse (2,:);
De = ellipse (3,:);
Fe = ellipse (4,:);
Ge = ellipse (5,:); %Coefficiants for the ellipse (Be-Ge)
draw_ellipse(1,0,1,Dc,Fc,Gc,'c');
draw_ellipse(1,0,Co,Do,Fo,Go,'r');
draw_ellipse(1,Be,Ce,De,Fe,Ge,'m');

% Residuals

rescircle =  sum([x.^2 + y.^2 + Dc.*x + Fc.*y + Gc.*ones(10,1)].^2);
resoval = sum([x.^2 + Co*(y.^2) + Do*x + Fo*y + Go.*ones(10,1)] .^2);
resellipse = sum([x.^2 + Be.*(x.*y) + Ce.*(y.^2) + De.*x + Fe.*y + Ge.*ones(10,1)].^2);
fprintf('The aquared residual of the circle was %-5.2f.\n' , rescircle)
fprintf('The squared residual of the oval was %-5.2f.\n' , resoval)
fprintf('The squared residual of the ellipse was %-5.2f.\n' , resellipse)

%% Ways to improve or extend this assignment
% It may sound like brown-nosing, but I really like the assignment the way it is. It
% tests pretty much everyting we have earned thus far. It takes some time,
% but isn't ridiculously long. And I like how so early i the course, we can
% create something tha actually is a useable program. 


% Conclusions
%I like that this assignment tested I wide range of skills. It helped
%solidify a lot of different concepts in my mind.
