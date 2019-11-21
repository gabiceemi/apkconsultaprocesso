--
-- PostgreSQL database dump
--

-- Dumped from database version 9.2.21
-- Dumped by pg_dump version 9.2.21
-- Started on 2017-06-16 18:06:39

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

DROP DATABASE postgres;
--
-- TOC entry 1999 (class 1262 OID 12002)
-- Name: postgres; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Portuguese_Brazil.1252' LC_CTYPE = 'Portuguese_Brazil.1252';


ALTER DATABASE postgres OWNER TO postgres;

\connect postgres

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 2000 (class 1262 OID 12002)
-- Dependencies: 1999
-- Name: postgres; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE postgres IS 'default administrative connection database';


--
-- TOC entry 2 (class 3079 OID 11727)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2003 (class 0 OID 0)
-- Dependencies: 2
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 1 (class 3079 OID 16384)
-- Name: adminpack; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;


--
-- TOC entry 2004 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION adminpack; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 170 (class 1259 OID 16431)
-- Name: andamento; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE andamento (
    "_ID" integer NOT NULL,
    "dtAndamento" text,
    ocorrencia text,
    "despachoProcesso" text,
    "idProcesso" integer,
    "idDepto" integer
);


ALTER TABLE public.andamento OWNER TO postgres;

--
-- TOC entry 171 (class 1259 OID 16437)
-- Name: andamento__ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "andamento__ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."andamento__ID_seq" OWNER TO postgres;

--
-- TOC entry 2005 (class 0 OID 0)
-- Dependencies: 171
-- Name: andamento__ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "andamento__ID_seq" OWNED BY andamento."_ID";


--
-- TOC entry 172 (class 1259 OID 16439)
-- Name: cidadao; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE cidadao (
    "_ID" integer NOT NULL,
    "nomeCidadao" text,
    "cpfOUcnpj" text
);


ALTER TABLE public.cidadao OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 16445)
-- Name: cidadao__ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "cidadao__ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."cidadao__ID_seq" OWNER TO postgres;

--
-- TOC entry 2006 (class 0 OID 0)
-- Dependencies: 173
-- Name: cidadao__ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "cidadao__ID_seq" OWNED BY cidadao."_ID";


--
-- TOC entry 174 (class 1259 OID 16447)
-- Name: departamento; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE departamento (
    "_ID" integer NOT NULL,
    "nomeDepartamento" text
);


ALTER TABLE public.departamento OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 16453)
-- Name: departamento__ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "departamento__ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."departamento__ID_seq" OWNER TO postgres;

--
-- TOC entry 2007 (class 0 OID 0)
-- Dependencies: 175
-- Name: departamento__ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "departamento__ID_seq" OWNED BY departamento."_ID";


--
-- TOC entry 176 (class 1259 OID 16455)
-- Name: funcionario; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE funcionario (
    "_ID" integer NOT NULL,
    "nomeFuncionario" text
);


ALTER TABLE public.funcionario OWNER TO postgres;

--
-- TOC entry 177 (class 1259 OID 16461)
-- Name: funcionario__ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "funcionario__ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."funcionario__ID_seq" OWNER TO postgres;

--
-- TOC entry 2008 (class 0 OID 0)
-- Dependencies: 177
-- Name: funcionario__ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "funcionario__ID_seq" OWNED BY funcionario."_ID";


--
-- TOC entry 178 (class 1259 OID 16463)
-- Name: processo; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE processo (
    "_ID" integer NOT NULL,
    "numProcesso" text,
    "dtProcesso" text,
    "obsProcesso" text,
    "horaProcesso" text,
    "idTipo" integer,
    "idFuncionario" integer,
    "idTitular" integer,
    "idRequerente" integer
);


ALTER TABLE public.processo OWNER TO postgres;

--
-- TOC entry 179 (class 1259 OID 16469)
-- Name: processo__ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "processo__ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."processo__ID_seq" OWNER TO postgres;

--
-- TOC entry 2009 (class 0 OID 0)
-- Dependencies: 179
-- Name: processo__ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "processo__ID_seq" OWNED BY processo."_ID";


--
-- TOC entry 180 (class 1259 OID 16471)
-- Name: tipo; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tipo (
    "_ID" integer NOT NULL,
    "nomeTipo" text
);


ALTER TABLE public.tipo OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 16477)
-- Name: tipo__ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "tipo__ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."tipo__ID_seq" OWNER TO postgres;

--
-- TOC entry 2010 (class 0 OID 0)
-- Dependencies: 181
-- Name: tipo__ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "tipo__ID_seq" OWNED BY tipo."_ID";


--
-- TOC entry 1847 (class 2604 OID 16479)
-- Name: _ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY andamento ALTER COLUMN "_ID" SET DEFAULT nextval('"andamento__ID_seq"'::regclass);


--
-- TOC entry 1848 (class 2604 OID 16480)
-- Name: _ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cidadao ALTER COLUMN "_ID" SET DEFAULT nextval('"cidadao__ID_seq"'::regclass);


--
-- TOC entry 1849 (class 2604 OID 16481)
-- Name: _ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY departamento ALTER COLUMN "_ID" SET DEFAULT nextval('"departamento__ID_seq"'::regclass);


--
-- TOC entry 1850 (class 2604 OID 16482)
-- Name: _ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY funcionario ALTER COLUMN "_ID" SET DEFAULT nextval('"funcionario__ID_seq"'::regclass);


--
-- TOC entry 1851 (class 2604 OID 16483)
-- Name: _ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY processo ALTER COLUMN "_ID" SET DEFAULT nextval('"processo__ID_seq"'::regclass);


--
-- TOC entry 1852 (class 2604 OID 16484)
-- Name: _ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipo ALTER COLUMN "_ID" SET DEFAULT nextval('"tipo__ID_seq"'::regclass);


--
-- TOC entry 1983 (class 0 OID 16431)
-- Dependencies: 170
-- Data for Name: andamento; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO andamento ("_ID", "dtAndamento", ocorrencia, "despachoProcesso", "idProcesso", "idDepto") VALUES (1, '30/02/2016', 'Abertura do Processo', 'Indefinido', 1, 1);
INSERT INTO andamento ("_ID", "dtAndamento", ocorrencia, "despachoProcesso", "idProcesso", "idDepto") VALUES (2, '25/07/2015', 'Abertura do Processo', 'Indefinido', 2, 2);
INSERT INTO andamento ("_ID", "dtAndamento", ocorrencia, "despachoProcesso", "idProcesso", "idDepto") VALUES (3, '12/04/2014', 'Abertura do Processo', 'Indefinido', 3, 3);
INSERT INTO andamento ("_ID", "dtAndamento", ocorrencia, "despachoProcesso", "idProcesso", "idDepto") VALUES (4, '05/10/2016', 'Etapa 2', 'Indefinido', 1, 2);
INSERT INTO andamento ("_ID", "dtAndamento", ocorrencia, "despachoProcesso", "idProcesso", "idDepto") VALUES (5, '21/08/2015', 'Etapa 2', 'Indefinido', 2, 3);
INSERT INTO andamento ("_ID", "dtAndamento", ocorrencia, "despachoProcesso", "idProcesso", "idDepto") VALUES (6, '25/12/2014', 'Etapa 2', 'Indefinido', 3, 1);
INSERT INTO andamento ("_ID", "dtAndamento", ocorrencia, "despachoProcesso", "idProcesso", "idDepto") VALUES (7, '12/12/2016', 'Etapa 3', 'Indefinido', 1, 2);
INSERT INTO andamento ("_ID", "dtAndamento", ocorrencia, "despachoProcesso", "idProcesso", "idDepto") VALUES (8, '30/10/2015', 'Etapa 3', 'Indefinido', 2, 3);


--
-- TOC entry 2011 (class 0 OID 0)
-- Dependencies: 171
-- Name: andamento__ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"andamento__ID_seq"', 8, true);


--
-- TOC entry 1985 (class 0 OID 16439)
-- Dependencies: 172
-- Data for Name: cidadao; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO cidadao ("_ID", "nomeCidadao", "cpfOUcnpj") VALUES (1, 'Jonnathan Riquelmo', '01819901050');
INSERT INTO cidadao ("_ID", "nomeCidadao", "cpfOUcnpj") VALUES (2, 'Dee Fialho', '02834768005');
INSERT INTO cidadao ("_ID", "nomeCidadao", "cpfOUcnpj") VALUES (3, 'Gabi Ceemi', '10706431901');
INSERT INTO cidadao ("_ID", "nomeCidadao", "cpfOUcnpj") VALUES (4, 'Valentina Mariane Luna', '41766785506');
INSERT INTO cidadao ("_ID", "nomeCidadao", "cpfOUcnpj") VALUES (5, 'Mariane Bárbara Rocha', '47200302465');
INSERT INTO cidadao ("_ID", "nomeCidadao", "cpfOUcnpj") VALUES (6, 'Rayssa Larissa Maria Costa', '47237670026');
INSERT INTO cidadao ("_ID", "nomeCidadao", "cpfOUcnpj") VALUES (7, 'Otávio Elias Pietro Monteiro', '27339838490');
INSERT INTO cidadao ("_ID", "nomeCidadao", "cpfOUcnpj") VALUES (8, 'Yuri Antonio Diogo Campos', '14502408018');
INSERT INTO cidadao ("_ID", "nomeCidadao", "cpfOUcnpj") VALUES (9, 'Vitor Pedro Samuel Moura', '59715261540');
INSERT INTO cidadao ("_ID", "nomeCidadao", "cpfOUcnpj") VALUES (10, 'Lavínia Marina dos Santos', '12691560554');


--
-- TOC entry 2012 (class 0 OID 0)
-- Dependencies: 173
-- Name: cidadao__ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"cidadao__ID_seq"', 10, true);


--
-- TOC entry 1987 (class 0 OID 16447)
-- Dependencies: 174
-- Data for Name: departamento; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO departamento ("_ID", "nomeDepartamento") VALUES (1, 'Secretaria de Administração');
INSERT INTO departamento ("_ID", "nomeDepartamento") VALUES (2, 'Secretaria de Fazenda');
INSERT INTO departamento ("_ID", "nomeDepartamento") VALUES (3, 'Secretaria de Obras e Serviços Urbanos');


--
-- TOC entry 2013 (class 0 OID 0)
-- Dependencies: 175
-- Name: departamento__ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"departamento__ID_seq"', 3, true);


--
-- TOC entry 1989 (class 0 OID 16455)
-- Dependencies: 176
-- Data for Name: funcionario; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO funcionario ("_ID", "nomeFuncionario") VALUES (1, 'Letícia Ana Lima');
INSERT INTO funcionario ("_ID", "nomeFuncionario") VALUES (2, 'Nathan Miguel Pereira');
INSERT INTO funcionario ("_ID", "nomeFuncionario") VALUES (3, 'Kevin Iago dos Santos');
INSERT INTO funcionario ("_ID", "nomeFuncionario") VALUES (4, 'Levi Alexandre Lucca Almeida');
INSERT INTO funcionario ("_ID", "nomeFuncionario") VALUES (5, 'Isabella Ester da Silva');


--
-- TOC entry 2014 (class 0 OID 0)
-- Dependencies: 177
-- Name: funcionario__ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"funcionario__ID_seq"', 5, true);


--
-- TOC entry 1991 (class 0 OID 16463)
-- Dependencies: 178
-- Data for Name: processo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO processo ("_ID", "numProcesso", "dtProcesso", "obsProcesso", "horaProcesso", "idTipo", "idFuncionario", "idTitular", "idRequerente") VALUES (1, '1/2016', '30/02/2016', 'obsobsobs', '20:48', 2, 1, 2, 1);
INSERT INTO processo ("_ID", "numProcesso", "dtProcesso", "obsProcesso", "horaProcesso", "idTipo", "idFuncionario", "idTitular", "idRequerente") VALUES (2, '2/2015', '25/07/2015', 'obsobsobs obsobsobs', '08:30', 3, 2, 3, 2);
INSERT INTO processo ("_ID", "numProcesso", "dtProcesso", "obsProcesso", "horaProcesso", "idTipo", "idFuncionario", "idTitular", "idRequerente") VALUES (3, '3/2014', '12/04/2014', 'obsobs', '10:00', 4, 3, 1, 3);


--
-- TOC entry 2015 (class 0 OID 0)
-- Dependencies: 179
-- Name: processo__ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"processo__ID_seq"', 3, true);


--
-- TOC entry 1993 (class 0 OID 16471)
-- Dependencies: 180
-- Data for Name: tipo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO tipo ("_ID", "nomeTipo") VALUES (1, 'Anuênio');
INSERT INTO tipo ("_ID", "nomeTipo") VALUES (2, 'Biênio');
INSERT INTO tipo ("_ID", "nomeTipo") VALUES (3, 'Triênio');
INSERT INTO tipo ("_ID", "nomeTipo") VALUES (4, 'Quadriênio');
INSERT INTO tipo ("_ID", "nomeTipo") VALUES (5, 'Quinquênio');
INSERT INTO tipo ("_ID", "nomeTipo") VALUES (6, 'Sexênio');
INSERT INTO tipo ("_ID", "nomeTipo") VALUES (7, 'Septênio');
INSERT INTO tipo ("_ID", "nomeTipo") VALUES (8, 'Octênio');
INSERT INTO tipo ("_ID", "nomeTipo") VALUES (9, 'Novênio');
INSERT INTO tipo ("_ID", "nomeTipo") VALUES (10, 'Decênio');


--
-- TOC entry 2016 (class 0 OID 0)
-- Dependencies: 181
-- Name: tipo__ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"tipo__ID_seq"', 10, true);


--
-- TOC entry 1856 (class 2606 OID 16486)
-- Name: andamento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY andamento
    ADD CONSTRAINT andamento_pkey PRIMARY KEY ("_ID");


--
-- TOC entry 1858 (class 2606 OID 16488)
-- Name: cidadao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY cidadao
    ADD CONSTRAINT cidadao_pkey PRIMARY KEY ("_ID");


--
-- TOC entry 1860 (class 2606 OID 16490)
-- Name: departamento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY departamento
    ADD CONSTRAINT departamento_pkey PRIMARY KEY ("_ID");


--
-- TOC entry 1862 (class 2606 OID 16492)
-- Name: funcionario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT funcionario_pkey PRIMARY KEY ("_ID");


--
-- TOC entry 1868 (class 2606 OID 16494)
-- Name: processo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY processo
    ADD CONSTRAINT processo_pkey PRIMARY KEY ("_ID");


--
-- TOC entry 1870 (class 2606 OID 16496)
-- Name: tipo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tipo
    ADD CONSTRAINT tipo_pkey PRIMARY KEY ("_ID");


--
-- TOC entry 1853 (class 1259 OID 16497)
-- Name: andamento_idDepto; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "andamento_idDepto" ON andamento USING btree ("idDepto");


--
-- TOC entry 1854 (class 1259 OID 16498)
-- Name: andamento_idProcesso; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "andamento_idProcesso" ON andamento USING btree ("idProcesso");


--
-- TOC entry 1863 (class 1259 OID 16499)
-- Name: processo_idFuncionario; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "processo_idFuncionario" ON processo USING btree ("idFuncionario");


--
-- TOC entry 1864 (class 1259 OID 16500)
-- Name: processo_idRequerente; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "processo_idRequerente" ON processo USING btree ("idRequerente");


--
-- TOC entry 1865 (class 1259 OID 16501)
-- Name: processo_idTipo; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "processo_idTipo" ON processo USING btree ("idTipo");


--
-- TOC entry 1866 (class 1259 OID 16502)
-- Name: processo_idTitular; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "processo_idTitular" ON processo USING btree ("idTitular");


--
-- TOC entry 1871 (class 2606 OID 16503)
-- Name: andamento_idDepto_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY andamento
    ADD CONSTRAINT "andamento_idDepto_fkey" FOREIGN KEY ("idDepto") REFERENCES departamento("_ID") ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1872 (class 2606 OID 16508)
-- Name: andamento_idProcesso_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY andamento
    ADD CONSTRAINT "andamento_idProcesso_fkey" FOREIGN KEY ("idProcesso") REFERENCES processo("_ID") ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1873 (class 2606 OID 16513)
-- Name: processo_idFuncionario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY processo
    ADD CONSTRAINT "processo_idFuncionario_fkey" FOREIGN KEY ("idFuncionario") REFERENCES funcionario("_ID") ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1874 (class 2606 OID 16518)
-- Name: processo_idRequerente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY processo
    ADD CONSTRAINT "processo_idRequerente_fkey" FOREIGN KEY ("idRequerente") REFERENCES cidadao("_ID") ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1875 (class 2606 OID 16523)
-- Name: processo_idTipo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY processo
    ADD CONSTRAINT "processo_idTipo_fkey" FOREIGN KEY ("idTipo") REFERENCES tipo("_ID") ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1876 (class 2606 OID 16528)
-- Name: processo_idTitular_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY processo
    ADD CONSTRAINT "processo_idTitular_fkey" FOREIGN KEY ("idTitular") REFERENCES cidadao("_ID") ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2002 (class 0 OID 0)
-- Dependencies: 8
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2017-06-16 18:06:39

--
-- PostgreSQL database dump complete
--

