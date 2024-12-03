// 댓글 관련 요소들
const viewPostCommentList = document.getElementById("viewPost-comment-list");
const viewPostCommentInput = document.getElementById("viewPost-comment-input");
const viewPostAddCommentBtn = document.getElementById("viewPost-add-comment");

// 댓글 추가 기능
viewPostAddCommentBtn.addEventListener("click", async () => {
    const commentText = viewPostCommentInput.value.trim();
    if (commentText === "") {
        alert("댓글 내용을 입력해주세요.");
        return;
    }

    try {
        // 서버로 댓글 추가 요청 (POST 요청)
        const response = await fetch('/mung/comments/add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                postId: postId,  // 게시글 ID (게시글에 해당하는 댓글을 달기 위한)
                content: commentText,  // 댓글 내용
            }),
        });

        if (!response.ok) {
            throw new Error('댓글 추가 실패');
        }

        // 댓글을 추가 후 화면에 표시
        const newComment = await response.json();  // 서버에서 반환된 댓글 객체
        const commentItem = document.createElement("li");
        commentItem.className = "viewPost-comment-item";
        commentItem.innerHTML = `
            <span>${newComment.content}</span>
            <button class="viewPost-delete-comment" data-comment-id="${newComment.id}">삭제</button>
        `;
        viewPostCommentList.appendChild(commentItem);
        viewPostCommentInput.value = "";

        // 댓글 삭제 이벤트 추가
        commentItem.querySelector(".viewPost-delete-comment").addEventListener("click", async () => {
            if (confirm("이 댓글을 삭제하시겠습니까?")) {
                try {
                    const commentId = commentItem.querySelector(".viewPost-delete-comment").getAttribute('data-comment-id');
                    const deleteResponse = await fetch(`/mung/comments/delete/${commentId}`, { method: 'DELETE' });
                    if (!deleteResponse.ok) throw new Error('댓글 삭제 실패');
                    commentItem.remove(); // 댓글 삭제 후 UI에서 제거
                } catch (error) {
                    alert('댓글 삭제 중 오류 발생');
                }
            }
        });
    } catch (error) {
        alert('댓글 추가 중 오류 발생');
    }
});

// 게시물 관련 기능
const viewPostEditButton = document.getElementById("viewPost-edit");
const viewPostDeleteButton = document.getElementById("viewPost-delete");

// 게시물 수정 기능
viewPostEditButton.addEventListener("click", () => {
    window.location.href = `/support/posts/edit/${postId}`;
});


