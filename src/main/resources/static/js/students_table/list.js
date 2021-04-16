const box = document.querySelector('.container .card:last-child .face.face2');

let mensPicture = ['../../static/picture/man_01.jpg',
    '../../static/picture/man_02.jpg',
    '../../static/picture/man_03.jpg',
    '../../static/picture/man_04.jpg',
    '../../static/picture/man_05.jpg',
    '../../static/picture/man_06.jpg',
    '../../static/picture/man_07.jpg',
    '../../static/picture/man_08.jpg'];

let womenPicture = ['../../static/picture/woman_01.jpg',
    '../../static/picture/woman_02.jpg',
    '../../static/picture/woman_03.jpg',
    '../../static/picture/woman_04.jpg',
    '../../static/picture/woman_05.jpg',
    '../../static/picture/woman_06.jpg',
    '../../static/picture/woman_07.jpg'];

let sizeMens = mensPicture.length;
let sizeWomen = womenPicture.length;

box.forEach(item => {

    if(item & sizeWomen >= 0) {
        sizeWomen = --womenPicture.length;
        item.style.backgroundImage = womenPicture[sizeWomen];

    } else if(item & sizeMens >= 0) {
        sizeMens = --mensPicture.length;
        item.style.backgroundImage = mensPicture[sizeMens];
    }
});