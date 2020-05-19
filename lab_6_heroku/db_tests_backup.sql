--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

-- Started on 2020-05-07 17:42:23

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

ALTER TABLE ONLY public.marks DROP CONSTRAINT fkq1nqkh9v9e06d5wu9sasgbk50;
ALTER TABLE ONLY public.marks DROP CONSTRAINT fkbtel34858snm9d51d5t6llucq;
ALTER TABLE ONLY public.answers DROP CONSTRAINT fk6tx462pg6av6gxv19teyuhmx5;
ALTER TABLE ONLY public.users DROP CONSTRAINT fk51371ndcn1xcm2o7b5s5rg7pd;
ALTER TABLE ONLY public.questions DROP CONSTRAINT fk39urelwybna3imr59yrxhiplb;
ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
ALTER TABLE ONLY public.tests DROP CONSTRAINT tests_pkey;
ALTER TABLE ONLY public.students DROP CONSTRAINT students_pkey;
ALTER TABLE ONLY public.questions DROP CONSTRAINT questions_pkey;
ALTER TABLE ONLY public.marks DROP CONSTRAINT marks_pkey;
ALTER TABLE ONLY public.logs DROP CONSTRAINT logs_pkey;
ALTER TABLE ONLY public.answers DROP CONSTRAINT answers_pkey;
DROP TABLE public.users;
DROP TABLE public.tests;
DROP TABLE public.students;
DROP TABLE public.questions;
DROP TABLE public.marks;
DROP TABLE public.logs;
DROP SEQUENCE public.hibernate_sequence;
DROP TABLE public.answers;
DROP SCHEMA public;
--
-- TOC entry 3 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA public;


--
-- TOC entry 2889 (class 0 OID 0)
-- Dependencies: 3
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_table_access_method = heap;

--
-- TOC entry 203 (class 1259 OID 16606)
-- Name: answers; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.answers (
    id integer NOT NULL,
    answer character varying(255),
    isright boolean NOT NULL,
    question_id integer
);


--
-- TOC entry 202 (class 1259 OID 16604)
-- Name: answers_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

ALTER TABLE public.answers ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.answers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 213 (class 1259 OID 16668)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 215 (class 1259 OID 16677)
-- Name: logs; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.logs (
    id integer NOT NULL,
    ip character varying(255),
    log character varying(255)
);


--
-- TOC entry 214 (class 1259 OID 16675)
-- Name: logs_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

ALTER TABLE public.logs ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.logs_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 205 (class 1259 OID 16613)
-- Name: marks; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.marks (
    id integer NOT NULL,
    date timestamp without time zone,
    rightanswers integer NOT NULL,
    student_id integer,
    test_id integer
);


--
-- TOC entry 204 (class 1259 OID 16611)
-- Name: marks_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

ALTER TABLE public.marks ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.marks_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 207 (class 1259 OID 16620)
-- Name: questions; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.questions (
    id integer NOT NULL,
    question character varying(255),
    test_id integer
);


--
-- TOC entry 206 (class 1259 OID 16618)
-- Name: questions_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

ALTER TABLE public.questions ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.questions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 209 (class 1259 OID 16627)
-- Name: students; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.students (
    id integer NOT NULL,
    name character varying(255)
);


--
-- TOC entry 208 (class 1259 OID 16625)
-- Name: students_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

ALTER TABLE public.students ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.students_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 211 (class 1259 OID 16634)
-- Name: tests; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tests (
    id integer NOT NULL,
    name character varying(255)
);


--
-- TOC entry 210 (class 1259 OID 16632)
-- Name: tests_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

ALTER TABLE public.tests ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.tests_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 212 (class 1259 OID 16660)
-- Name: users; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.users (
    id integer NOT NULL,
    login character varying(255),
    password character varying(255),
    usertype integer,
    student_id integer,
    lastvisitdate timestamp without time zone,
    visitcounter integer
);


--
-- TOC entry 2871 (class 0 OID 16606)
-- Dependencies: 203
-- Data for Name: answers; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.answers (id, answer, isright, question_id) VALUES (1, '19 сентября 1991', true, 1);
INSERT INTO public.answers (id, answer, isright, question_id) VALUES (2, '10 ноября 1990', false, 1);
INSERT INTO public.answers (id, answer, isright, question_id) VALUES (3, '29 августа 1991', false, 1);
INSERT INTO public.answers (id, answer, isright, question_id) VALUES (4, 'Идиш, русский, белорусский', false, 2);
INSERT INTO public.answers (id, answer, isright, question_id) VALUES (5, 'Русский', false, 2);
INSERT INTO public.answers (id, answer, isright, question_id) VALUES (6, 'Белорусский', false, 2);
INSERT INTO public.answers (id, answer, isright, question_id) VALUES (7, 'Русский, белорусский', true, 2);
INSERT INTO public.answers (id, answer, isright, question_id) VALUES (8, '14/3', false, 3);
INSERT INTO public.answers (id, answer, isright, question_id) VALUES (9, '28/3', true, 3);
INSERT INTO public.answers (id, answer, isright, question_id) VALUES (11, '14', false, 3);
INSERT INTO public.answers (id, answer, isright, question_id) VALUES (10, '28', false, 3);


--
-- TOC entry 2883 (class 0 OID 16677)
-- Dependencies: 215
-- Data for Name: logs; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.logs (id, ip, log) VALUES (1, '127.0.0.1', ' | with command = ''null''');
INSERT INTO public.logs (id, ip, log) VALUES (2, '127.0.0.1', ' | with command = ''null''');
INSERT INTO public.logs (id, ip, log) VALUES (3, '127.0.0.1', ' | with command = ''null''');
INSERT INTO public.logs (id, ip, log) VALUES (4, '127.0.0.1', ' | with command = ''null''');
INSERT INTO public.logs (id, ip, log) VALUES (5, '127.0.0.1', ' | with command = ''null''');
INSERT INTO public.logs (id, ip, log) VALUES (6, '127.0.0.1', ' | with command = ''register''');
INSERT INTO public.logs (id, ip, log) VALUES (7, '127.0.0.1', ' | with command = ''null''');
INSERT INTO public.logs (id, ip, log) VALUES (8, '127.0.0.1', ' | with command = ''null''');
INSERT INTO public.logs (id, ip, log) VALUES (9, '127.0.0.1', ' | with command = ''null''');
INSERT INTO public.logs (id, ip, log) VALUES (10, '127.0.0.1', ' | with command = ''null''');
INSERT INTO public.logs (id, ip, log) VALUES (11, '127.0.0.1', ' | with command = ''login''');
INSERT INTO public.logs (id, ip, log) VALUES (12, '127.0.0.1', ' | with command = ''logout''');
INSERT INTO public.logs (id, ip, log) VALUES (13, '127.0.0.1', ' | with command = ''null''');
INSERT INTO public.logs (id, ip, log) VALUES (14, '127.0.0.1', ' | with command = ''null''');
INSERT INTO public.logs (id, ip, log) VALUES (15, '127.0.0.1', ' | with command = ''register''');
INSERT INTO public.logs (id, ip, log) VALUES (16, '127.0.0.1', ' | with command = ''register''');
INSERT INTO public.logs (id, ip, log) VALUES (17, '127.0.0.1', ' | with command = ''null''');
INSERT INTO public.logs (id, ip, log) VALUES (18, '127.0.0.1', ' | with command = ''null''');
INSERT INTO public.logs (id, ip, log) VALUES (19, '127.0.0.1', ' | with command = ''null''');
INSERT INTO public.logs (id, ip, log) VALUES (20, '127.0.0.1', ' | with command = ''null''');
INSERT INTO public.logs (id, ip, log) VALUES (21, '127.0.0.1', ' | with command = ''null''');
INSERT INTO public.logs (id, ip, log) VALUES (22, '127.0.0.1', ' | with command = ''null''');
INSERT INTO public.logs (id, ip, log) VALUES (23, '127.0.0.1', ' | with command = ''logout''');
INSERT INTO public.logs (id, ip, log) VALUES (24, '127.0.0.1', ' | with command = ''logout''');
INSERT INTO public.logs (id, ip, log) VALUES (25, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/ | date = Thu Apr 30 13:21:47 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (26, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/ | date = Thu Apr 30 13:21:49 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (27, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/ | date = Thu Apr 30 13:21:50 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (28, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/auth/login.jsp | date = Thu Apr 30 13:21:51 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (29, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/css/styles.css | date = Thu Apr 30 13:21:51 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (30, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/singleServlet | with command = ''login'' | date = Thu Apr 30 13:22:07 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (31, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/singleServlet | with command = ''logout'' | date = Thu Apr 30 13:22:53 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (32, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/singleServlet | with command = ''logout'' | date = Thu Apr 30 13:22:55 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (33, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/singleServlet | with command = ''logout'' | date = Thu Apr 30 13:22:58 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (34, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/ | date = Thu Apr 30 13:24:23 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (35, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/singleServlet | with command = ''logout'' | date = Thu Apr 30 13:24:25 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (36, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/ | date = Thu Apr 30 13:25:45 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (37, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/singleServlet | with command = ''logout'' | date = Thu Apr 30 13:25:46 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (38, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/singleServlet | with command = ''logout'' | date = Thu Apr 30 13:25:47 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (39, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/singleServlet | with command = ''logout'' | date = Thu Apr 30 13:25:48 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (40, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/ | date = Thu Apr 30 13:25:48 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (41, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/auth/login.jsp | date = Thu Apr 30 13:25:49 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (42, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/css/styles.css | date = Thu Apr 30 13:25:49 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (43, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/singleServlet | with command = ''login'' | date = Thu Apr 30 13:25:51 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (44, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/singleServlet | with command = ''logout'' | date = Thu Apr 30 13:25:51 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (45, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/ | date = Thu Apr 30 14:04:01 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (46, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/ | date = Thu Apr 30 14:04:02 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (47, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/ | date = Thu Apr 30 14:04:37 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (48, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/singleServlet | with command = ''logout'' | date = Thu Apr 30 14:04:39 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (49, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/ | date = Thu Apr 30 14:04:39 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (50, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/singleServlet | with command = ''logout'' | date = Thu Apr 30 14:04:40 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (51, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/ | date = Thu Apr 30 14:04:40 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (52, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/singleServlet | with command = ''logout'' | date = Thu Apr 30 14:04:41 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (53, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/ | date = Thu Apr 30 14:04:41 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (54, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/auth/login.jsp | date = Thu Apr 30 14:04:43 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (55, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/css/styles.css | date = Thu Apr 30 14:04:43 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (56, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/singleServlet | with command = ''login'' | date = Thu Apr 30 14:04:45 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (57, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/ | date = Thu Apr 30 14:06:29 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (58, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/singleServlet | with command = ''insertAllData'' | date = Thu Apr 30 14:06:32 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (59, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/ | date = Thu Apr 30 14:28:46 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (60, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/singleServlet | with command = ''insertAllData'' | date = Thu Apr 30 14:28:47 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (61, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/auth/login.jsp | date = Thu Apr 30 14:28:54 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (62, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/css/styles.css | date = Thu Apr 30 14:28:54 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (63, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/singleServlet | with command = ''login'' | date = Thu Apr 30 14:28:55 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (64, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/singleServlet | with command = ''logout'' | date = Thu Apr 30 14:28:58 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (65, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/singleServlet | with command = ''logout'' | date = Thu Apr 30 14:28:59 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (66, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/singleServlet | with command = ''logout'' | date = Thu Apr 30 14:29:00 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (67, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/singleServlet | with command = ''logout'' | date = Thu Apr 30 14:29:01 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (68, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/auth/register.jsp | date = Thu Apr 30 14:29:02 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (69, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/css/styles.css | date = Thu Apr 30 14:29:02 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (70, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/singleServlet | with command = ''register'' | date = Thu Apr 30 14:29:04 MSK 2020');


--
-- TOC entry 2873 (class 0 OID 16613)
-- Dependencies: 205
-- Data for Name: marks; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.marks (id, date, rightanswers, student_id, test_id) VALUES (1, '2020-03-16 10:45:45.181', -1, 1, 1);
INSERT INTO public.marks (id, date, rightanswers, student_id, test_id) VALUES (2, '2020-03-16 10:45:45.284', -1, 2, 1);
INSERT INTO public.marks (id, date, rightanswers, student_id, test_id) VALUES (3, '2020-04-20 17:23:57.255', 0, 1, 1);
INSERT INTO public.marks (id, date, rightanswers, student_id, test_id) VALUES (4, '2020-04-20 17:35:36.299', 2, 1, 1);
INSERT INTO public.marks (id, date, rightanswers, student_id, test_id) VALUES (5, '2020-04-21 17:11:01.316', -1, 1, 2);


--
-- TOC entry 2875 (class 0 OID 16620)
-- Dependencies: 207
-- Data for Name: questions; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.questions (id, question, test_id) VALUES (1, 'Дата основания Республики Беларусь - ', 1);
INSERT INTO public.questions (id, question, test_id) VALUES (2, 'Официальные языки Республики Беларусь:', 1);
INSERT INTO public.questions (id, question, test_id) VALUES (3, 'Дана правильная четырехугольная пирамида. Вычислите ее объем, если ее высота равна 7 см, a сторона основания составляет – 2 см.', 2);


--
-- TOC entry 2877 (class 0 OID 16627)
-- Dependencies: 209
-- Data for Name: students; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.students (id, name) VALUES (1, 'Скорина Андрей');
INSERT INTO public.students (id, name) VALUES (2, 'Дмитрий Никитин');
INSERT INTO public.students (id, name) VALUES (3, 'A');


--
-- TOC entry 2879 (class 0 OID 16634)
-- Dependencies: 211
-- Data for Name: tests; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.tests (id, name) VALUES (1, 'История Беларуси');
INSERT INTO public.tests (id, name) VALUES (2, 'Математика');


--
-- TOC entry 2880 (class 0 OID 16660)
-- Dependencies: 212
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.users (id, login, password, usertype, student_id, lastvisitdate, visitcounter) VALUES (21, 'A', '123', 0, 3, NULL, NULL);
INSERT INTO public.users (id, login, password, usertype, student_id, lastvisitdate, visitcounter) VALUES (22, 'Teacher', 'test', 1, NULL, '2020-04-30 14:28:56.024', 28);
INSERT INTO public.users (id, login, password, usertype, student_id, lastvisitdate, visitcounter) VALUES (1, 'Andrew', '123', 0, 1, '2020-04-21 15:04:34.251', 40);


--
-- TOC entry 2890 (class 0 OID 0)
-- Dependencies: 202
-- Name: answers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.answers_id_seq', 1, false);


--
-- TOC entry 2891 (class 0 OID 0)
-- Dependencies: 213
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.hibernate_sequence', 21, true);


--
-- TOC entry 2892 (class 0 OID 0)
-- Dependencies: 214
-- Name: logs_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.logs_id_seq', 70, true);


--
-- TOC entry 2893 (class 0 OID 0)
-- Dependencies: 204
-- Name: marks_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.marks_id_seq', 5, true);


--
-- TOC entry 2894 (class 0 OID 0)
-- Dependencies: 206
-- Name: questions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.questions_id_seq', 3, true);


--
-- TOC entry 2895 (class 0 OID 0)
-- Dependencies: 208
-- Name: students_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.students_id_seq', 3, true);


--
-- TOC entry 2896 (class 0 OID 0)
-- Dependencies: 210
-- Name: tests_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.tests_id_seq', 1, false);


--
-- TOC entry 2726 (class 2606 OID 16610)
-- Name: answers answers_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.answers
    ADD CONSTRAINT answers_pkey PRIMARY KEY (id);


--
-- TOC entry 2738 (class 2606 OID 16684)
-- Name: logs logs_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.logs
    ADD CONSTRAINT logs_pkey PRIMARY KEY (id);


--
-- TOC entry 2728 (class 2606 OID 16617)
-- Name: marks marks_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.marks
    ADD CONSTRAINT marks_pkey PRIMARY KEY (id);


--
-- TOC entry 2730 (class 2606 OID 16624)
-- Name: questions questions_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.questions
    ADD CONSTRAINT questions_pkey PRIMARY KEY (id);


--
-- TOC entry 2732 (class 2606 OID 16631)
-- Name: students students_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.students
    ADD CONSTRAINT students_pkey PRIMARY KEY (id);


--
-- TOC entry 2734 (class 2606 OID 16638)
-- Name: tests tests_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tests
    ADD CONSTRAINT tests_pkey PRIMARY KEY (id);


--
-- TOC entry 2736 (class 2606 OID 16667)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 2742 (class 2606 OID 16654)
-- Name: questions fk39urelwybna3imr59yrxhiplb; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.questions
    ADD CONSTRAINT fk39urelwybna3imr59yrxhiplb FOREIGN KEY (test_id) REFERENCES public.tests(id);


--
-- TOC entry 2743 (class 2606 OID 16670)
-- Name: users fk51371ndcn1xcm2o7b5s5rg7pd; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fk51371ndcn1xcm2o7b5s5rg7pd FOREIGN KEY (student_id) REFERENCES public.students(id);


--
-- TOC entry 2739 (class 2606 OID 16639)
-- Name: answers fk6tx462pg6av6gxv19teyuhmx5; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.answers
    ADD CONSTRAINT fk6tx462pg6av6gxv19teyuhmx5 FOREIGN KEY (question_id) REFERENCES public.questions(id);


--
-- TOC entry 2741 (class 2606 OID 16649)
-- Name: marks fkbtel34858snm9d51d5t6llucq; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.marks
    ADD CONSTRAINT fkbtel34858snm9d51d5t6llucq FOREIGN KEY (test_id) REFERENCES public.tests(id);


--
-- TOC entry 2740 (class 2606 OID 16644)
-- Name: marks fkq1nqkh9v9e06d5wu9sasgbk50; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.marks
    ADD CONSTRAINT fkq1nqkh9v9e06d5wu9sasgbk50 FOREIGN KEY (student_id) REFERENCES public.students(id);


-- Completed on 2020-05-07 17:42:24

--
-- PostgreSQL database dump complete
--
