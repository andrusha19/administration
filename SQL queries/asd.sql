--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.5
-- Dumped by pg_dump version 9.6.5

-- Started on 2017-09-24 22:43:39

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12387)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2131 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 185 (class 1259 OID 16386)
-- Name: persons; Type: TABLE; Schema: public; Owner: user
--

CREATE TABLE persons (
    personid integer,
    lastname character varying(255),
    firstname text,
    address text,
    city character varying(255)
);


ALTER TABLE persons OWNER TO "user";

--
-- TOC entry 186 (class 1259 OID 16418)
-- Name: users; Type: TABLE; Schema: public; Owner: user
--

CREATE TABLE users (
    name character varying(128) NOT NULL,
    password character(32) NOT NULL,
    email character varying(128) NOT NULL,
    registration_time timestamp without time zone NOT NULL,
    last_edit_time timestamp without time zone NOT NULL,
    role character varying(128) NOT NULL
);


ALTER TABLE users OWNER TO "user";

--
-- TOC entry 2123 (class 0 OID 16386)
-- Dependencies: 185
-- Data for Name: persons; Type: TABLE DATA; Schema: public; Owner: user
--

COPY persons (personid, lastname, firstname, address, city) FROM stdin;
\.


--
-- TOC entry 2124 (class 0 OID 16418)
-- Dependencies: 186
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: user
--

COPY users (name, password, email, registration_time, last_edit_time, role) FROM stdin;
user1	24c9e15e52afc47c225b757e7bee1f9d	user1@gmail.com	2017-09-24 15:42:08.746743	2017-09-24 15:42:08.746743	user
user2	7e58d63b60197ceb55a1c487989a3720	user2@gmail.com	2017-09-24 15:42:08.746743	2017-09-24 15:42:08.746743	user
user3	92877af70a45fd6a2ed7fe81e1236b78	user3@gmail.com	2017-09-24 15:42:08.746743	2017-09-24 15:42:08.746743	user
user4	3f02ebe3d7929b091e3d8ccfde2f3bc6	user4@gmail.com	2017-09-24 15:42:08.746743	2017-09-24 15:42:08.746743	user
user7	3e0469fb134991f8f75a2760e409c6ed	user7@gmail.com	2017-09-24 15:42:08.746743	2017-09-24 15:42:08.746743	user
user8	7668f673d5669995175ef91b5d171945	user8@gmail.com	2017-09-24 15:42:08.746743	2017-09-24 15:42:08.746743	user
user9	8808a13b854c2563da1a5f6cb2130868	user9@gmail.com	2017-09-24 15:42:08.746743	2017-09-24 15:42:08.746743	user
user11	03aa1a0b0375b0461c1b8f35b234e67a	user11@gmail.com	2017-09-24 15:42:08.746743	2017-09-24 15:42:08.746743	user
user12	d781eaae8248db6ce1a7b82e58e60435	user12@gmail.com	2017-09-24 15:42:08.746743	2017-09-24 15:42:08.746743	user
user13	d09979d794a6ee60d836f884739f7196	user13@gmail.com	2017-09-24 15:42:08.746743	2017-09-24 15:42:08.746743	user
user14	ef06d5cbf35386ff2203d186eeff7923	user14@gmail.com	2017-09-24 15:42:08.746743	2017-09-24 15:42:08.746743	user
user15	726dedc0d6788b05f486730edcc0e871	user15@gmail.com	2017-09-24 15:42:08.746743	2017-09-24 15:42:08.746743	user
user16	8a62f0beaa5ae938956f5ea290321336	user16@gmail.com	2017-09-24 15:42:08.746743	2017-09-24 15:42:08.746743	user
user17	2b4233ebec7a45e1fb8ddd1aab794f7b	user17@gmail.com	2017-09-24 15:42:08.746743	2017-09-24 15:42:08.746743	user
user18	7ac18a1893e1d2bd5b46958ce1d2a8d0	user18@gmail.com	2017-09-24 15:42:08.746743	2017-09-24 15:42:08.746743	user
user19	2baab43f784b5b530b5347a50490bb0a	user19@gmail.com	2017-09-24 15:42:08.746743	2017-09-24 15:42:08.746743	user
user20	10880c7f4e4209eeda79711e1ea1723e	user20@gmail.com	2017-09-24 15:42:08.746743	2017-09-24 15:42:08.746743	user
admin1	e00cf25ad42683b3df678c61f42c6bda	admin1@gmail.com	2017-09-24 15:42:08.746743	2017-09-24 15:42:08.746743	admin
админ	e61dfbc3c9b44a7e7bcae19b2f35375d	админ@gmail.com	2017-09-24 15:42:08.746743	2017-09-24 15:42:08.746743	admin
юзер	c321e0cbb28661c70ac6b24410c40c55	юзер@gmail.com	2017-09-24 15:42:08.746743	2017-09-24 15:42:08.746743	user
юзер6	c321e0cbb28661c70ac6b24410c40c55	юзер6	2017-09-24 16:27:43.398	2017-09-24 16:46:12.648	user
юзер5	c321e0cbb28661c70ac6b24410c40c55	юзер5	2017-09-24 16:24:33.443	2017-09-24 16:52:24.301	user
юзер9	c321e0cbb28661c70ac6b24410c40c55	юзер	2017-09-24 16:52:59.694	2017-09-24 16:52:59.694	user
юзер4	c321e0cbb28661c70ac6b24410c40c55	юзер4	2017-09-24 16:18:34.907	2017-09-24 17:36:44.348	admin
юзер3	c321e0cbb28661c70ac6b24410c40c55	юзер3	2017-09-24 16:13:04.131	2017-09-24 17:36:52.693	user
юзер12	3192d2ccaccfec0403961a36cefc400f	юзер12	2017-09-24 17:39:17.678	2017-09-24 17:39:43.338	admin
admin	21232f297a57a5a743894a0e4a801fc3	asd	2017-09-24 19:08:44.27	2017-09-24 19:09:07.771	admin
asd	7815696ecbf1c96e6894b779456d330e	sad	2017-09-24 19:49:33.18	2017-09-24 19:49:33.18	user
user10	990d67a9f94696b1abe2dccf06900322	user10@gmail.com	2017-09-24 15:42:08.746	2017-09-24 19:59:15.311	user
юзер11	c321e0cbb28661c70ac6b24410c40c55	юзер11	2017-09-24 17:20:51.329	2017-09-24 20:04:33.356	admin
\.


--
-- TOC entry 2005 (class 2606 OID 16422)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (name);


-- Completed on 2017-09-24 22:43:40

--
-- PostgreSQL database dump complete
--

