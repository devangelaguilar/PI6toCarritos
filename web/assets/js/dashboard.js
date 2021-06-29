$(document).ready(() => {
    addResizeListeners();
    setSidenavListeners();
    setUserDropdownListener();
    setMenuClickListener();
    setSidenavCloseListener();
});
  
const sidenavEl = $('.sidenav');
const gridEl = $('.grid');
const SIDENAV_ACTIVE_CLASS = 'sidenav--active';
const GRID_NO_SCROLL_CLASS = 'grid--noscroll';

function toggleClass(el, className) {
    if (el.hasClass(className)) {
        el.removeClass(className);
    } else {
        el.addClass(className);
    }
}
  
  // Dropdown del avatar de la derecha superior
  function setUserDropdownListener() {
    const userAvatar = $('.header__avatar');
  
    userAvatar.on('click', function(e) {
      const dropdown = $(this).children('.dropdown');
      toggleClass(dropdown, 'dropdown--active');
    });
  }
  
  // En caso de necesitar una lista dropdown de los botones del menú
  function setSidenavListeners() {
    const subHeadings = $('.navList__subheading'); console.log('subHeadings: ', subHeadings);
    const SUBHEADING_OPEN_CLASS = 'navList__subheading--open';
    const SUBLIST_HIDDEN_CLASS = 'subList--hidden';
  
    subHeadings.each((i, subHeadingEl) => {
      $(subHeadingEl).on('click', (e) => {
        const subListEl = $(subHeadingEl).siblings();
  
        if (subHeadingEl) {
          toggleClass($(subHeadingEl), SUBHEADING_OPEN_CLASS);
        }
  
        if (subListEl && subListEl.length === 1) {
          toggleClass($(subListEl), SUBLIST_HIDDEN_CLASS);
        }
      });
    });
  }
  
  function toggleClass(el, className) {
    if (el.hasClass(className)) {
      el.removeClass(className);
    } else {
      el.addClass(className);
    }
  }
  
  // Checa si la pantalla cambió de tamaño para modificar el menú
  function addResizeListeners() {
    $(window).resize(function(e) {
      const width = window.innerWidth; console.log('width: ', width);
  
      if (width > 750) {
        sidenavEl.removeClass(SIDENAV_ACTIVE_CLASS);
        gridEl.removeClass(GRID_NO_SCROLL_CLASS);
      }
    });
  }
  
  // Click al botón de menú en móvil para abrir el menú
  function setMenuClickListener() {
    $('.header__menu').on('click', function(e) { console.log('clicked menu icon');
      toggleClass(sidenavEl, SIDENAV_ACTIVE_CLASS);
      toggleClass(gridEl, GRID_NO_SCROLL_CLASS);
    });
  }
  
  // Click al botón de menú en móvil para abrir el menú
  function setSidenavCloseListener() {
    $('.sidenav__brand-close').on('click', function(e) {
      toggleClass(sidenavEl, SIDENAV_ACTIVE_CLASS);
      toggleClass(gridEl, GRID_NO_SCROLL_CLASS);
    });
  }
  