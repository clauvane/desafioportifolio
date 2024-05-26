<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>

<div class="container">
 <div class="panel panel-primary">
 <br>
  <div class="panel-heading">
   <h3>Lista de Membros</h3>
  </div>
  <div class="panel-body">
   <table class="table table-striped">
    <thead>
     <tr>
      <th style="width: 35%">Pessoa</th>
      <th style="width: 35%">Projeto</th>
      <th style="width: 35%">Ações</th>
     </tr>
    </thead>
    <tbody>
     <c:forEach items="${membros}" var="membro">
      <tr>
       <td>${membro.membroId.pessoa.nome}</td>
       <td>${membro.membroId.projeto.nome}</td>
       <td></td>
      </tr>
     </c:forEach>
    </tbody>
   </table>
  </div>
 </div>

<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>