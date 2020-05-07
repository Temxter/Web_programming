INSERT INTO public.students (id, name) VALUES (1, 'Скорина Андрей');
INSERT INTO public.students (id, name) VALUES (2, 'Дмитрий Никитин');
INSERT INTO public.students (id, name) VALUES (3, 'A');

INSERT INTO public.users (id, login, password, usertype, student_id, lastvisitdate, visitcounter) VALUES (21, 'A', '123', 0, 3, null, null);
INSERT INTO public.users (id, login, password, usertype, student_id, lastvisitdate, visitcounter) VALUES (22, 'Teacher', 'test', 1, null, '2020-04-30 13:25:51.236000', 24);
INSERT INTO public.users (id, login, password, usertype, student_id, lastvisitdate, visitcounter) VALUES (1, 'Andrew', '123', 0, 1, '2020-04-21 15:04:34.251000', 40);

INSERT INTO public.tests (id, name) VALUES (1, 'История Беларуси');
INSERT INTO public.tests (id, name) VALUES (2, 'Математика');

INSERT INTO public.questions (id, question, test_id) VALUES (1, 'Дата основания Республики Беларусь - ', 1);
INSERT INTO public.questions (id, question, test_id) VALUES (2, 'Официальные языки Республики Беларусь:', 1);
INSERT INTO public.questions (id, question, test_id) VALUES (3, 'Дана правильная четырехугольная пирамида. Вычислите ее объем, если ее высота равна 7 см, a сторона основания составляет – 2 см.', 2);

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

INSERT INTO public.marks (id, date, rightanswers, student_id, test_id) VALUES (1, '2020-03-16 10:45:45.181000', -1, 1, 1);
INSERT INTO public.marks (id, date, rightanswers, student_id, test_id) VALUES (2, '2020-03-16 10:45:45.284000', -1, 2, 1);
INSERT INTO public.marks (id, date, rightanswers, student_id, test_id) VALUES (3, '2020-04-20 17:23:57.255000', 0, 1, 1);
INSERT INTO public.marks (id, date, rightanswers, student_id, test_id) VALUES (4, '2020-04-20 17:35:36.299000', 2, 1, 1);
INSERT INTO public.marks (id, date, rightanswers, student_id, test_id) VALUES (5, '2020-04-21 17:11:01.316000', -1, 1, 2);

INSERT INTO public.logs (id, ip, log) VALUES (42, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/css/styles.css | date = Thu Apr 30 13:25:49 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (43, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/singleServlet | with command = ''login'' | date = Thu Apr 30 13:25:51 MSK 2020');
INSERT INTO public.logs (id, ip, log) VALUES (44, '127.0.0.1', 'Request to URL = http://localhost:8080/Web/singleServlet | with command = ''logout'' | date = Thu Apr 30 13:25:51 MSK 2020');


