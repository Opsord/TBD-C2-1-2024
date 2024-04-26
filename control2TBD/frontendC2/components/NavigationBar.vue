<script setup lang="ts">
const route = useRoute()

const links = [{
  label: 'Inicio',
  icon: 'i-heroicons-home',
  to: '/'
},
{
  label: 'Tareas pendientes',
  icon: 'i-heroicons-academic-cap',
  to: '/tasks'
},
{
  label: 'Tareas finalizadas',
  icon: 'i-heroicons-check-circle',
  to: '/pending'
},
{
  label: 'Perfil',
  avatar: {
    src: 'https://avatars.githubusercontent.com/u/739984?v=4'
  },
  badge: 100

}
]
</script>

<template>
  <UHorizontalNavigation :links="links"
    class="border-b border-gray-200 dark:border-gray-800 flex items-center justify-center align-middle" />
</template>
