--
-- PostgreSQL database dump
--

-- Dumped from database version 13.1
-- Dumped by pg_dump version 13.1

-- Started on 2021-06-21 15:58:36 +03

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 201 (class 1259 OID 41257)
-- Name: coffee; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.coffee (
    type character varying(255) NOT NULL,
    price integer
);


ALTER TABLE public.coffee OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 41298)
-- Name: coffeeorder; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.coffeeorder (
    id bigint NOT NULL,
    amount integer,
    cost integer,
    delivery character varying(255),
    deliverytime character varying(255),
    coffee_type character varying(255) NOT NULL
);


ALTER TABLE public.coffeeorder OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 41253)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- TOC entry 3258 (class 0 OID 41257)
-- Dependencies: 201
-- Data for Name: coffee; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.coffee (type, price) FROM stdin;
Arabic	100
Diablo	666
Indian	50
\.


--
-- TOC entry 3259 (class 0 OID 41298)
-- Dependencies: 202
-- Data for Name: coffeeorder; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.coffeeorder (id, amount, cost, delivery, deliverytime, coffee_type) FROM stdin;
42	1000	600	Courier delivery	10:30-20:10	Indian
70	100	666	Self delivery	10:30-11:22	Diablo
74	100	100	Self delivery	10:30-11:20	Arabic
\.


--
-- TOC entry 3265 (class 0 OID 0)
-- Dependencies: 200
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 75, true);


--
-- TOC entry 3123 (class 2606 OID 41261)
-- Name: coffee coffee_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.coffee
    ADD CONSTRAINT coffee_pkey PRIMARY KEY (type);


--
-- TOC entry 3125 (class 2606 OID 41305)
-- Name: coffeeorder coffeeorder_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.coffeeorder
    ADD CONSTRAINT coffeeorder_pkey PRIMARY KEY (id);


--
-- TOC entry 3126 (class 2606 OID 41306)
-- Name: coffeeorder fk2pe7njgaxcdk8k3cqqukicpi; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.coffeeorder
    ADD CONSTRAINT fk2pe7njgaxcdk8k3cqqukicpi FOREIGN KEY (coffee_type) REFERENCES public.coffee(type);


-- Completed on 2021-06-21 15:58:36 +03

--
-- PostgreSQL database dump complete
--

