<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>

<div class="container">
 <div class="panel panel-primary">
  <div class="panel-heading">
  <br>
   <div>
    <a type="button" class="btn btn-primary btn-md" href="/portifolio/projeto/salvar">Cadastrar Projeto</a>
   </div>
   <hr>
   <h3>Lista de Projetos</h3>
  </div>
  <div class="panel-body">
   <table class="table table-striped">
    <thead>
     <tr>
      <th width="10%">Nome</th>
      <th width="10%">Gerente</th>
      <th width="8%">Status</th>
      <th width="8%">Data Início</th>
      <th width="8%">Data Previsão Fim</th>
      <th width="8%">Data Fim</th>
      <th width="8%">Orçamento</th>
      <th width="10%">Descrição</th>
      <th width="10%">Risco</th>
      <th width="20%">Ações</th>
     </tr>
    </thead>
    <tbody>
     <c:forEach items="${projetos}" var="projeto">
      <tr>
       <td>${projeto.nome}</td>
       <td>${projeto.gerente.nome}</td>
       <td>
            <c:choose>
                 <c:when test = "${projeto.status eq 'EM_ANALISE'}">Em Análise</c:when>
                 <c:when test = "${projeto.status eq 'ANALISE_REALIZADA'}">Análise Realizada</c:when>
                 <c:when test = "${projeto.status eq 'ANALISE_APROVADA'}">Análise Aprovada</c:when>
                 <c:when test = "${projeto.status eq 'INICIADO'}">Iniciado</c:when>
                 <c:when test = "${projeto.status eq 'PLANEJADO'}">Planejado</c:when>
                 <c:when test = "${projeto.status eq 'EM_ANDAMENTO'}">Em Andamento</c:when>
                 <c:when test = "${projeto.status eq 'ENCERRADO'}">Encerrado</c:when>
                 <c:when test = "${projeto.status eq 'CANCELADO'}">Cancelado</c:when>
                 <c:otherwise>${projeto.status}</c:otherwise>
            </c:choose>
       </td>
       <td><fmt:formatDate value="${projeto.dataInicio}" pattern="dd/MM/yyyy" /></td>
       <td><fmt:formatDate value="${projeto.dataPrevisaoFim}" pattern="dd/MM/yyyy" /></td>
       <td><fmt:formatDate value="${projeto.dataFim}" pattern="dd/MM/yyyy" /></td>
       <td>${projeto.orcamento}</td>
       <td>${projeto.descricao}</td>
       <td>
            <c:choose>
                 <c:when test = "${projeto.risco eq 'BAIXO'}">Baixo Risco</c:when>
                 <c:when test = "${projeto.risco eq 'MEDIO'}">Medio Risco</c:when>
                 <c:when test = "${projeto.risco eq 'ALTO'}">Alto Risco</c:when>
                 <c:otherwise>${projeto.risco}</c:otherwise>
            </c:choose>
       </td>
       <td>
        <c:if test = "${projeto.id != null}">
           <a type="button" class="btn btn-primary" href="/portifolio/projeto/editar?id=${projeto.id}">Editar</a>

               <button type="button"
                       class="btn btn-danger"
                       data-bs-target="#removerModal"
                       data-bs-toggle="modal"
                       data-nome="O Projeto ${projeto.nome} será removido."
                       data-link="/portifolio/projeto/remover?id=${projeto.id}">
                       Remover
                </button>
        </c:if>
       </td>
      </tr>
     </c:forEach>
    </tbody>
   </table>
  </div>
 </div>

<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>