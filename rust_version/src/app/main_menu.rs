use relm4::adw::prelude::*;
use relm4::prelude::*;

pub struct MainMenuModel;

#[derive(Debug)]
pub enum MainMenuOutput {
    GoToCreateServer,
    GoToCreateClient,
}

#[relm4::component(pub)]
impl SimpleComponent for MainMenuModel {
    type Init = ();
    type Input = ();
    type Output = MainMenuOutput;

    view! {
        #[root]
        gtk::Box {
            set_orientation: gtk::Orientation::Vertical,
            set_hexpand: true,
            set_margin_all: 32,

            gtk::Label {
                set_label: "Socket Trench",
                set_margin_all: 32,
                set_css_classes: &["title-1"],
            },
            adw::PreferencesGroup {
                adw::ActionRow {
                    set_title: "Criar partida",
                    set_activatable: true,
                    add_suffix = &gtk::Image {
                        set_icon_name: Some("go-next-symbolic")
                    },
                    connect_activated[sender] => move |_| {
                        sender.output(Self::Output::GoToCreateServer).unwrap();
                    },
                },
                adw::ActionRow {
                    set_title: "Conectar à partida",
                    set_activatable: true,
                    add_suffix = &gtk::Image {
                        set_icon_name: Some("go-next-symbolic")
                    },
                    connect_activated[sender] => move |_| {
                        sender.output(Self::Output::GoToCreateClient).unwrap();
                    },
                },
            },
        }
    }

    fn init(
        _init: Self::Init,
        root: &Self::Root,
        sender: ComponentSender<Self>,
    ) -> ComponentParts<Self> {
        let model = Self;
        let widgets = view_output!();

        ComponentParts { model, widgets }
    }
}
