-- Table: public."Course"

-- DROP TABLE public."Course";

CREATE TABLE public."Course"
(
    id character(36) COLLATE pg_catalog."default" NOT NULL,
    subject character varying(120) COLLATE pg_catalog."default",
    "teacherId" character(36) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "Course_pkey" PRIMARY KEY (id),
    CONSTRAINT course_teacher FOREIGN KEY ("teacherId")
        REFERENCES public."Teacher" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Course"
    OWNER to postgres;

-- Index: fki_course_teacher

-- DROP INDEX public.fki_course_teacher;

CREATE INDEX fki_course_teacher
    ON public."Course" USING btree
    ("teacherId" COLLATE pg_catalog."default")
    TABLESPACE pg_default;
	
-- Table: public."Exam"

-- DROP TABLE public."Exam";

CREATE TABLE public."Exam"
(
    id character(36) COLLATE pg_catalog."default" NOT NULL,
    date date,
    room character varying(50) COLLATE pg_catalog."default",
    "courseId" character(36) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "Exam_pkey" PRIMARY KEY (id),
    CONSTRAINT exam_course FOREIGN KEY ("courseId")
        REFERENCES public."Course" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Exam"
    OWNER to postgres;

-- Index: fki_exam_course

-- DROP INDEX public.fki_exam_course;

CREATE INDEX fki_exam_course
    ON public."Exam" USING btree
    ("courseId" COLLATE pg_catalog."default")
    TABLESPACE pg_default;
	
-- Table: public."Grade"

-- DROP TABLE public."Grade";

CREATE TABLE public."Grade"
(
    id character(36) COLLATE pg_catalog."default" NOT NULL,
    mark smallint,
    "studentId" character(36) COLLATE pg_catalog."default" NOT NULL,
    "examId" character(36) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "Grade_pkey" PRIMARY KEY (id),
    CONSTRAINT grade_exam FOREIGN KEY ("examId")
        REFERENCES public."Exam" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT grade_student FOREIGN KEY ("studentId")
        REFERENCES public."Student" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Grade"
    OWNER to postgres;

-- Index: fki_grade_exam

-- DROP INDEX public.fki_grade_exam;

CREATE INDEX fki_grade_exam
    ON public."Grade" USING btree
    ("examId" COLLATE pg_catalog."default")
    TABLESPACE pg_default;

-- Index: fki_grade_student

-- DROP INDEX public.fki_grade_student;

CREATE INDEX fki_grade_student
    ON public."Grade" USING btree
    ("studentId" COLLATE pg_catalog."default")
    TABLESPACE pg_default;
	
-- Table: public."MM_Student_Course"

-- DROP TABLE public."MM_Student_Course";

CREATE TABLE public."MM_Student_Course"
(
    id character(36) COLLATE pg_catalog."default" NOT NULL,
    "studentId" character(36) COLLATE pg_catalog."default" NOT NULL,
    "courseId" character(36) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "MM_Student_Course_pkey" PRIMARY KEY (id),
    CONSTRAINT course_student FOREIGN KEY ("courseId")
        REFERENCES public."Course" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT student_course FOREIGN KEY ("studentId")
        REFERENCES public."Student" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."MM_Student_Course"
    OWNER to postgres;

-- Index: fki_course_student

-- DROP INDEX public.fki_course_student;

CREATE INDEX fki_course_student
    ON public."MM_Student_Course" USING btree
    ("courseId" COLLATE pg_catalog."default")
    TABLESPACE pg_default;

-- Index: fki_student_course

-- DROP INDEX public.fki_student_course;

CREATE INDEX fki_student_course
    ON public."MM_Student_Course" USING btree
    ("studentId" COLLATE pg_catalog."default")
    TABLESPACE pg_default;
	
-- Table: public."Student"

-- DROP TABLE public."Student";

CREATE TABLE public."Student"
(
    id character(36) COLLATE pg_catalog."default" NOT NULL,
    surname character varying(40) COLLATE pg_catalog."default",
    name character varying(40) COLLATE pg_catalog."default",
    "personalNumber" character(13) COLLATE pg_catalog."default" NOT NULL,
    "studentUsername" character(30) COLLATE pg_catalog."default" NOT NULL,
    "identityCardNumber" character varying(30) COLLATE pg_catalog."default",
    address character varying(40) COLLATE pg_catalog."default",
    CONSTRAINT "Student_pkey" PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Student"
    OWNER to postgres;

-- Index: fki_student_teacher

-- DROP INDEX public.fki_student_teacher;

CREATE INDEX fki_student_teacher
    ON public."Student" USING btree
    ("studentUsername" COLLATE pg_catalog."default")
    TABLESPACE pg_default;
	
	-- Table: public."Teacher"

-- DROP TABLE public."Teacher";

CREATE TABLE public."Teacher"
(
    id character(36) COLLATE pg_catalog."default" NOT NULL,
    surname character varying(40) COLLATE pg_catalog."default",
    name character varying(40) COLLATE pg_catalog."default",
    "teacherUsername" character varying(30) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "Teacher_pkey" PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Teacher"
    OWNER to postgres;

-- Index: fki_teacher_user

-- DROP INDEX public.fki_teacher_user;

CREATE INDEX fki_teacher_user
    ON public."Teacher" USING btree
    ("teacherUsername" COLLATE pg_catalog."default")
    TABLESPACE pg_default;
	
-- Table: public."User"

-- DROP TABLE public."User";

CREATE TABLE public."User"
(
    username character varying(30) COLLATE pg_catalog."default" NOT NULL,
    password character(44) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "User_pkey" PRIMARY KEY (username)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."User"
    OWNER to postgres;
	
INSERT INTO public."User"(
	username, password)
	VALUES (Teacher, qAtWiiN/UDkdLx+Xvq+ZVk4z0uHIouXKwhztpwFXAxI=);
	
INSERT INTO public."Teacher"(
	id, surname, name, "teacherUsername")
	VALUES (123, Ion, Popescu, Teacher);
	
INSERT INTO public."User"(
	username, password)
	VALUES (Student, qAtWiiN/UDkdLx+Xvq+ZVk4z0uHIouXKwhztpwFXAxI=);
	
INSERT INTO public."Student"(
	id, surname, name, "personalNumber", "studentUsername", "identityCardNumber", address)
	VALUES (12345, Maria, Pop, 123456789, Student, 98765, Cluj-Napoca);