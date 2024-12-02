$(document).ready(function () {
    // 모달 닫기 버튼 클릭 시
    document.querySelector('.btn-close').addEventListener('click', function () {
        // backdrop 제거
        document.querySelector('.modal-backdrop').remove();
    });

    // 모든 .square5_edit_btn 요소에 클릭 이벤트 리스너 추가

    const today = new Date();
    document.querySelectorAll("button[data-resv-date]").forEach(button => {
        const resvDate = new Date(button.getAttribute("data-resv-date"));
        if (resvDate <= today) {
            button.style.display = "none";
        } else {
            button.addEventListener("click", () => {
                if (confirm("예약을 취소하시겠습니까?")) {
                    const resvNum = button.getAttribute("data-resv-num");
                    // 예약 취소 API 호출
                    fetch(`/mung/kakaopay/refund`, {
                        method: "POST",
                        headers: {
                            "Content-Type": "application/json"
                        },
                        body: JSON.stringify({
                            orderNum: resvNum
                        })
                    })
                        .then(response => {
                            if (response.ok) {
                                alert("환불이 완료되었습니다.");
                                location.reload();
                            } else {
                                alert("환불 처리에 실패했습니다.");
                            }
                        })
                        .catch(err => alert("오류가 발생했습니다: " + err));
                }
            });
        }
    });

    function confirmCancel(resvNum) {
        if (confirm("예약을 취소하시겠습니까?")) {
            // 예약 취소 API 호출
            fetch(`/reservation/cancel/${resvNum}`, {
                method: "POST",
            })
                .then(response => {
                    if (response.ok) {
                        alert("예약이 취소되었습니다.");
                        location.reload(); // 페이지 새로고침
                    } else {
                        alert("예약 취소에 실패했습니다. 다시 시도해주세요.");
                    }
                })
                .catch(error => {
                    console.error("Error cancelling reservation:", error);
                    alert("오류가 발생했습니다. 다시 시도해주세요.");
                });
        }
    }

// 강아지 프로필 사진 등록
    $('#dogImage').submit(function (event) {
        event.preventDefault(); // 폼 기본 제출을 방지
        var formData = new FormData(this);

        $.ajax({
            url: '/mypage/uploadImage',  // 백엔드의 URL
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            success: function (response) {
                $('#message').html('<div class="alert alert-success">사진이 성공적으로 업로드되었습니다!</div>');
            },
            error: function () {
                $('#message').html('<div class="alert alert-danger">업로드 중 오류가 발생했습니다.</div>');
            }
        });
    });

// weight dropdown
    const weightSelect = document.getElementById("weight");
// 1.0kg부터 40.0kg까지 범위 생성
    for (let i = 1.0; i <= 40.0; i += 0.1) {
        const option = document.createElement("option");
        const value = i.toFixed(1); // 소수점 한 자리로 고정
        option.value = parseFloat(value);
        console.log(typeof option.value)
        option.textContent = `${value}kg`;
        weightSelect.appendChild(option);
    }

// breed dropdown
    const breedSelect = document.getElementById("breed");
// 견종 목록
    const breeds = [
        "시츄",
        "푸들",
        "불독",
        "골든 리트리버",
        "비글",
        "치와와",
        "닥스훈트",
        "셰퍼드",
        "도베르만",
        "허스키",
        "말티즈",
        "믹스견 (Mixed)" // 마지막에 믹스견 추가
    ];

// 견종 옵션 생성
    breeds.forEach(breed => {
        const option = document.createElement("option");
        option.value = breed; // 값
        option.textContent = breed; // 표시 텍스트
        breedSelect.appendChild(option);
    });
// weight dropdown
    const weightSelectEdit = document.getElementById("editWeight");
// 1.0kg부터 40.0kg까지 범위 생성
    for (let i = 1.0; i <= 40.0; i += 0.1) {
        const option = document.createElement("option");
        const value = i.toFixed(1); // 소수점 한 자리로 고정
        option.value = parseFloat(value);
        console.log(typeof option.value)
        option.textContent = `${value}kg`;
        weightSelectEdit.appendChild(option);
    }

// breed dropdown
    const breedSelectEdit = document.getElementById("editBreed");
// 견종 목록

// 견종 옵션 생성
    breeds.forEach(breed => {
        const option = document.createElement("option");
        option.value = breed; // 값
        option.textContent = breed; // 표시 텍스트
        breedSelectEdit.appendChild(option);
    });
})

function loadDogInfo(dogId) {
    console.log("helloo");
    console.log(dogId);

    // dogId가 올바르게 전달되었는지 확인
    if (!dogId) {
        console.error("dogId is null or undefined");
        return; // dogId가 없으면 더 이상 진행하지 않음
    }

    // AJAX 요청을 보냄
    $.ajax({
        url: `/mung/mypage/dogProfile/${dogId}`,
        method: "get",
        dataType: 'json',
        success: function (dog) {
            console.log("====================================")
            console.log(dog); // dog 정보 확인

            var modalElement = document.getElementById('exampleModal');
            if (!modalElement) {
                console.error("Modal element not found!");
                return;
            }

            var modal = new bootstrap.Modal(modalElement);
            modal.show(); // 모달 열기
            function updateDogProfileImage(imageUrl) {
                const dogProfileImage = document.querySelector('#dogProfileImage');
                dogProfileImage.src = imageUrl ? '/uploads/' + imageUrl : '/default-image.png';
            }


            let today = new Date();
            let year = today.getFullYear(); // 년도
            let month = today.getMonth() + 1;  // 월
            let date = today.getDate();  // 날짜
            let sysDate = new Date(`${year}/${month}/${date}`);

            let targetDate = new Date(dog.birthDate);

            let yearsDifference = sysDate.getFullYear() - targetDate.getFullYear();

            console.log(`년도 차이: ${yearsDifference}`); // 원하는 결과 출력

            // 프로필 데이터 업데이트
            document.getElementById('dog-name').textContent = dog.name;
            document.getElementById('dog-age').textContent = yearsDifference+"세";
            document.getElementById('dog-gender').textContent = dog.gender;
            document.getElementById('dog-breed').textContent = dog.breed;
            document.getElementById('dog-birthDate').textContent = dog.birthDate;
            document.getElementById('dog-weight').textContent = dog.weight + 'kg';








            document.getElementById('test').textContent = dog.imageUrl;

            document.querySelector('#dogProfileImage').src = "/mung/mypage/" + dog.imageUrl || '/default-image.png'; // 기본 이미지 처리

            const myModalEl = document.getElementById('dogProfileEditModal')
            myModalEl.addEventListener('shown.bs.modal', event => {
                // do something...
                console.log("sssssssssssssssssssssssssssssssssssssssssssssss");
                document.getElementById('editUserId').value = dog.userId;
                document.getElementById('editDogId').value = dog.id;
                document.getElementById('editName').value = dog.name;
                if (dog.gender === 'M') {
                    $('#editMale').prop('checked', true);
                } else {
                    $('#editFemale').prop('checked', true);
                }
                document.getElementById('editBreed').value = dog.breed;
                document.getElementById('editBirthDate').value = dog.birthDate;
                document.getElementById('editWeight').value = dog.weight;
                document.getElementById('editWeight').value = dog.weight;
                document.querySelector('#editDogImage').src = "/mung/mypage/" + dog.imageUrl || '/default-image.png'; // 기본 이미지 처리

            })
            // 프로필 데이터 업데이트
// 모달 닫기 버튼 클릭 시

        },
        /* document.getElementById('dog-name').innerHTML = dog.name;
       document.getElementById('dog-age').innerHTML = dog.age;
       document.getElementById('dog-gender').innerHTML = dog.gender;
       document.getElementById('dog-breed').innerHTML = dog.breed;
       document.getElementById('dog-birthDate').innerHTML = dog.birthDate;
       document.getElementById('dog-weight').innerHTML = dog.weight + 'kg';
       document.querySelector('.square5_profile_img').src = dog.imageUrl || '/default-image.png'; // 기본 이미지 처리
   },*/
        error: function (xhr, status, error) {
            console.error('Error loading dog profile:', error);
            alert('Failed to load dog profile. Please try again.');
        }
    });
    loadDogInfoForEdit(dogId)
}

function editClick(dogId) {
    const editButtons = document.querySelectorAll(".square5_edit_btn");
    editButtons.forEach(button => {
        button.addEventListener("click", function () {
            console.log("Dog ID:", dogId); // 디버깅
            if (dogId) {
                loadDogInfoForEdit(dogId); // 해당 dogId로 데이터 로드
            } else {
                console.error("dogId가 설정되지 않았습니다.");
            }
        });
    });


}

function loadDogInfoForEdit(dogId) {
    console.log(dogId);
    $.ajax({
        url: `/mung/mypage/dog/${dogId}`,
        method: 'GET',
        dataType: 'json',
        success: function (dog) {
            // 기존 데이터로 폼 채우기
            console.log(dog);

            $('#userId').val(dog.userId);
            $('#editDogId').val(dog.id);
            $('#editName').val(dog.name);
            $('#editPreview').attr('src', `/mung/mypage/${dog.imageUrl}`);

            // 성별 라디오 버튼 설정
            if (dog.gender === 'M') {
                $('#editMale').prop('checked', true);
            } else {
                $('#editFemale').prop('checked', true);
            }

            // 견종 선택
            $('#editBreed').val(dog.breed);

            // 생일 설정
            $('#editBirthDate').val(dog.birthDate);

            // 몸무게 설정
            $('#editWeight').val(dog.weight);
        },
        error: function (xhr, status, error) {
            console.error('Error loading dog info:', status, error);
        }
    });
    updateDog(dog.dogId);
}


// 이미지 미리보기 기능
document.getElementById('editDogImage').addEventListener('change', function (e) {
    const file = e.target.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function (e) {
            document.getElementById('editPreview').src = e.target.result;
        }
        reader.readAsDataURL(file);
    }
});

function updateDog(dogId) {
    const updatedData = {
        name: $("#editName").val(),
        breed: $("#editBreed").val(),
        weight: $("#editWeight").val(),
        gender: $("input[name='gender']:checked").val()
    };

    $.ajax({
        url: `/mung/mypage/dog/edit/${dogId}`, // 수정할 반려견의 ID를 포함
        type: "PUT", // HTTP 메서드
        contentType: "application/json",
        data: JSON.stringify(updatedData),
        success: function (response) {
            alert("반려견 정보가 성공적으로 수정되었습니다!");
            console.log("Response:", response);
            location.reload();
        },
        error: function (xhr, status, error) {
            console.error("Error updating dog:", error);
            alert("반려견 정보를 수정하는 중 오류가 발생했습니다.");
        }
    });
}

function loadResvInfo(resvNo) {
    console.log("helloo");
    console.log(resvNo);

    // dogId가 올바르게 전달되었는지 확인
    if (!resvNo) {
        console.error("resvNo is null or undefined");
        return; // dogId가 없으면 더 이상 진행하지 않음
    }

    // AJAX 요청을 보냄
    $.ajax({
        url: `/mung/mypage/reservation/${resvNo}`,
        method: "get",
        dataType: 'json',
        success: function (resv) {
            console.log(resv); // dog 정보 확인

            var modalElement = document.getElementById('resvationModal');
            if (!modalElement) {
                console.error("Modal element not found!");
                return;
            }

            var modal = new bootstrap.Modal(modalElement);
            modal.show(); // 모달 열기
            // 프로필 데이터 업데이트
            document.getElementById('detailResvNum').textContent = resv.resvNum;
            document.getElementById('detailResvBranchName').textContent = resv.branchName;
            document.getElementById('detailResvDate').textContent = resv.formattedResvDate;
            document.getElementById('detailResvStart').textContent = resv.formattedStartTime;
            document.getElementById('detailResvEnd').textContent = resv.formattedEndTime;
// 모달 닫기 버튼 클릭 시
            document.querySelector('.btn-close').addEventListener('click', function () {
                // backdrop 제거
                document.querySelector('.modal-backdrop').remove();
            });

        },
        /* document.getElementById('dog-name').innerHTML = dog.name;
       document.getElementById('dog-age').innerHTML = dog.age;
       document.getElementById('dog-gender').innerHTML = dog.gender;
       document.getElementById('dog-breed').innerHTML = dog.breed;
       document.getElementById('dog-birthDate').innerHTML = dog.birthDate;
       document.getElementById('dog-weight').innerHTML = dog.weight + 'kg';
       document.querySelector('.square5_profile_img').src = dog.imageUrl || '/default-image.png'; // 기본 이미지 처리
   },*/
        error: function (xhr, status, error) {
            console.error('Error loading dog profile:', error);
            alert('Failed to load dog profile. Please try again.');
        }
    });

}


// 모달 초기화
document.addEventListener('DOMContentLoaded', function () {
    // 등록 모달이 닫힐 때 폼 초기화
    const registerModal = document.getElementById('dogRegisterModal');
    registerModal?.addEventListener('hidden.bs.modal', function () {
        document.querySelector('.square5_dog_register_form').reset();
        document.getElementById('preview').src = '../images/dog-placeholder.png';
    });
});