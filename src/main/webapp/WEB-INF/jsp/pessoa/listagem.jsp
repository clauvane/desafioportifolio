<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>

<div class="container">
 <div class="panel panel-primary">
  <div class="panel-heading">
  <br>
   <div>
    <a type="button" class="btn btn-primary btn-md" href="/portifolio/pessoa/salvar">Cadastrar Pessoa</a>
   </div>
   <hr>
   <h3>Lista de Pessoas</h3>
  </div>
  <div class="panel-body">
   <table class="table table-striped">
    <thead>
     <tr>
      <th style="width: 30%">Nome</th>
      <th style="width: 20%">CPF</th>
      <th style="width: 20%">Data de Nascimento</th>
      <th style="width: 5%">Funcionário</th>
      <th style="width: 5%">Gerente</th>
      <th style="width: 20%">Ações</th>
     </tr>
    </thead>
    <tbody>
     <c:forEach items="${pessoas}" var="pessoa">
      <tr>
       <td>${pessoa.nome}</td>
       <td>${pessoa.cpf}</td>
       <td><fmt:formatDate value="${pessoa.dataNascimento}" pattern="dd/MM/yyyy" /></td>
       <td>
            <c:choose>
                 <c:when test = "${pessoa.funcionario}">Sim</c:when>
                 <c:when test = "${!pessoa.funcionario}">Não</c:when>
                 <c:otherwise>"Não Informado"</c:otherwise>
            </c:choose>
       </td>
       <td>
                   <c:choose>
                        <c:when test = "${pessoa.gerente}">Sim</c:when>
                        <c:when test = "${!pessoa.gerente}">Não</c:when>
                        <c:otherwise>"Não Informado"</c:otherwise>
                   </c:choose>
              </td>
       <td>
        <c:if test = "${pessoa.id != null}">
           <a type="button" class="btn btn-primary" href="/portifolio/pessoa/editar?id=${pessoa.id}">Editar</a>
           <button type="button"
                   class="btn btn-danger"
                   data-bs-target="#removerModal"
                   data-bs-toggle="modal"
                   data-nome="A Pessoa ${pessoa.nome} será removida."
                   data-link="/portifolio/pessoa/remover?id=${pessoa.id}">
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