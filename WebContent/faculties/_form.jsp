 <form action="./?u=${faculty.code}" method="POST">
  <div class="form-group">
    <label for="code">Code</label>
    <input type="text" class="form-control" id="code" name="code" value="${faculty.code}">
  </div>
  <div class="form-group">
    <label for="name">Name</label>
    <input type="text" class="form-control" id="name" name="name" value="${faculty.name}">
  </div>
  <button type="submit" class="btn btn-default">Submit</button>
</form> 