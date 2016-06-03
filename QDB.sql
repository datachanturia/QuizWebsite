-- create schema if not exists Shaurma;


drop table if exists UsersAchievement;
drop table if exists TakenQuiz;
drop table if exists Challenges;
drop table if exists Requests;
drop table if exists Messages;
drop table if exists Friends;
drop table if exists Achievements;
drop table if exists Answers;
drop table if exists Questions;
drop table if exists Quiz;
drop table if exists Users;



create table if not exists Users(
	userID int not null auto_increment,
    username varchar(2048) CHARACTER SET utf8 default NULL, 
    pass varchar(2048) CHARACTER SET utf8 default NULL,
    mail varchar(2048) CHARACTER SET utf8 default NULL,
    photo varchar(2048) CHARACTER SET utf8 default NULL,
    creationdate datetime,
    isadmin tinyint(1) default 0,
    
    primary key (userID)
);

create table if not exists Quiz(
	quizID int not null auto_increment,
    quizname varchar(2048) CHARACTER SET utf8 default null,
    authorID int,
    score int default 0,
    category int,
    crationdate datetime,
    
    primary key (quizID),
    foreign key (authorID) references Users (userID)
);

create table if not exists Questions(
	questionID int not null auto_increment,
    question varchar(2048) CHARACTER SET utf8 default null,
    questiontype int,
    quizID int,
    
    primary key (questionID),
    foreign key (quizID) references Quiz (quizID)
);

create table if not exists Answers(
	answerID int not null auto_increment,
    answer varchar(2048) CHARACTER SET utf8 default null,
    iscorrect tinyint(1) default 0,
    questionID int,
    
    primary key (answerID),
    foreign key (questionID) references Questions (questionID)
);

create table if not exists Achievements(
	achievementID int not null auto_increment,
    achievement int,
    
    primary key (achievementID)
);

create table if not exists Friends(
	userID int,
    friendID int,
    crationdate datetime,
    
    foreign key (userID) references Users (userID),
    foreign key (friendID) references Users (userID)
);

create table if not exists Messages(
	messageID int not null auto_increment,
    senderID int,
    receiverID int,
    senddate datetime,
	isread tinyint(1) default 0,
    
    primary key (messageID),
    foreign key (senderID) references Users (userID),
    foreign key (receiverID) references Users (userID)
);

create table if not exists Requests(
	requestID int not null auto_increment,
    senderID int,
    receiverID int,
    senddate datetime,
    
    primary key (requestID),
    foreign key (senderID) references Users (userID),
    foreign key (receiverID) references Users (userID)
);

create table if not exists Challenges(
	challengeID int not null auto_increment,
    senderID int,
    receiverID int,
    quizID int,
    senddate datetime,
    
    primary key (challengeID),
    foreign key (senderID) references Users (userID),
    foreign key (receiverID) references Users (userID),
    foreign key (quizID) references Quiz (quizID)
);

create table if not exists TakenQuiz(
	userID int,
    quizID int,
    takedate datetime,
    score int default 0,
    
    foreign key (userID) references Users (userID),
    foreign key (quizID) references Quiz (quizID)
);

create table if not exists UsersAchievement(
	userID int,
    achievementID int,
    
    foreign key (userID) references Users (userID),
    foreign key (achievementID) references Achievements (achievementID)
);